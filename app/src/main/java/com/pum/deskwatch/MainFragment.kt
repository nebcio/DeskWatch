package com.pum.deskwatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pum.deskwatch.databinding.MainFragmentBinding

class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getTaskList(requireContext())

        val tasksAdapter = TasksListAdapter(TaskComparator(), false)
        binding.tasksRecyclerView.apply {
            adapter = tasksAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        observeTask(tasksAdapter)

        swipeToDelete(tasksAdapter)

        val themeSwitch: Switch = binding.themeSwitch
        // listener for switch
        themeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
        val modeSwitch: Switch = binding.modeSwitch
        // listener for switch
        modeSwitch.setOnCheckedChangeListener { _, isChecked ->
            layoutInflater.inflate(R.layout.main_fragment, null).keepScreenOn = isChecked
        }
//        if (isChecked) {
//            layoutInflater.inflate(R.layout.main_fragment, null).keepScreenOn = true
//            //getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
//        } else {
//            layoutInflater.inflate(R.layout.main_fragment, null).keepScreenOn = false
//            //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
//        }

        // button add task
        binding.addButton.setOnClickListener {
            // go to task fragment
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToTaskFragment2(-1)) // prepare id?
        }
    }

    private fun observeTask(taskAdapter: TasksListAdapter) {
        mainViewModel.tasks.observe(viewLifecycleOwner) { response ->
            taskAdapter.submitList(response)
        }
    }

    private fun swipeToDelete(adapter: TasksListAdapter) {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                mainViewModel.deleteTask(adapter.getTaskAt(viewHolder.adapterPosition), requireContext())
            }
        }).attachToRecyclerView(binding.tasksRecyclerView)
    }
}
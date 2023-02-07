package com.pum.deskwatch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.pum.deskwatch.databinding.TaskFragmentBinding

class TaskFragment : Fragment() {
    private lateinit var binding: TaskFragmentBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    private val itemId: Int by lazy { requireArguments().getInt("id") }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = TaskFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    private fun displayItem(item: Task){
        binding.titleEditText.setText(item.title)
        binding.detailsEditText.setText(item.description)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainViewModel.getTask(itemId).observe(viewLifecycleOwner, this::displayItem)

        binding.titleEditText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) { updateItem() }
        }

        binding.detailsEditText.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) { updateItem() }
        }
    }

    private fun updateItem() {
        val name = binding.titleEditText.text.toString()
        val description = binding.detailsEditText.text.toString()

        if (name.isNotEmpty() && itemId != -1){
            val item = Task(itemId, name, description, false)
            mainViewModel.updateTask(item)
        }
        else if (name.isNotEmpty() && itemId == -1){
            val id = if (mainViewModel.tasks.value?.lastIndex!! > 0) 0 else (mainViewModel.tasks.value?.lastIndex!! + 1)
            val item = Task(id, name, description, false)
            mainViewModel.addTask(item)
        }
        else{
            binding.titleEditText.error = "Podaj tytuł"
        }
    }
}
package com.azimut4946777.my_tasks

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.azimut4946777.my_tasks.databinding.FragmentTasksBinding
import com.hfad.tasks.TaskItemAdapter

class TasksFragment : Fragment() {
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        val view = binding.root
        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        val viewModelFactory = TasksViewModelFactory(dao)
        val viewModel = ViewModelProvider(
            this, viewModelFactory).get(TasksViewModel::class.java)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val adapter = TaskItemAdapter{taskId ->
            Toast.makeText(context,"Clicked task $taskId",Toast.LENGTH_SHORT).show()

        }
        binding.tasksList.adapter = adapter

        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.submitList(it)
            }
        })


        return view
    }
    fun aaaa() {

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
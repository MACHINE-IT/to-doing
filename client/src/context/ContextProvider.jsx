
import React, { useState } from 'react';
import TaskContext from './Context';


const ContextProvider = ({children}) => {

    const [myState, setMyState] = useState('initialValue');

    const [currentPage, setCurrentPage] = useState("my-day");

    const [tasks, setTasks] = useState([]);

    const addTask = (newTask) => {
      setTasks([...tasks, newTask]);
    };

    const removeTask = (taskId) => {
      const updatedTasks = tasks.filter((task) => task.id !== taskId);
      setTasks(updatedTasks);
    };

  return (
    <TaskContext.Provider value={{currentPage, setCurrentPage, myState, setMyState, tasks, addTask, removeTask}}>
        {children}
    </TaskContext.Provider>
  )
}

export default ContextProvider

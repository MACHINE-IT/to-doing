import React from 'react';
import { Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import ContextProvider from './context/ContextProvider';
import Login from './pages/auth/Login';
import Register from './pages/auth/Register';
import AssignedTo from './pages/tasks/AssignedTo';
import Home from './pages/tasks/Home';
import Important from './pages/tasks/Important';
import MyDay from './pages/tasks/MyDay';
import Planned from './pages/tasks/Planned';
import Tasks from './pages/tasks/Tasks';
import Testing from './pages/tasks/Testing';



function App() {
    const items = [
        { id: 1, title: 'Todo', count: 5 },
        { id: 2, title: 'In Progress', count: 10 },
    ];

    const clearFilters = () => {
        // Add logic to clear filters here
        console.log('Filters cleared');
    };

    return (
        <ContextProvider>
            <div className="App">
                <Router>
                    <Routes>
                        <Route element={<Home></Home>} path='/home'></Route>
                        <Route element={<MyDay></MyDay>} path='/my-day'></Route>
                        <Route element={<Planned></Planned>} path='/planned'></Route>
                        <Route element={<Important></Important>} path='/important'></Route>
                        <Route element={<AssignedTo></AssignedTo>} path='/assigned-to'></Route>
                        <Route element={<Tasks></Tasks>} path='/tasks'></Route>
                        <Route element={<Login></Login>} path='/login'></Route>
                        <Route element={<Register></Register>} path='/register'></Route>
                        <Route element={<Testing></Testing>} path='/testing'></Route>
                    </Routes>
                </Router>
            </div>
        </ContextProvider>
    );
}

export default App;

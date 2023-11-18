import React from "react";
import ExperimentDropdown from "./Tasks/ExperimentDropdown";
import { TasksView } from "./Tasks/TasksView";

const Home = () => {
    return<>
    <div>
        <h1>Hello, welcome to home page</h1>
        <TasksView />
        <ExperimentDropdown />
    </div>
    </>
}

export default Home;
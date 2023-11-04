import React from "react";
import ExperimentDropdown from "./Tasks/ExperimentDropdown";
import { TasksView } from "./Tasks/TasksView";

const Home = () => {
    return<>
    <div>
        {/* <Navbar></Navbar> */}
        <h1>Hello, welcome to home page</h1>
        {/*<DropDown />*/}
        <TasksView />
        <ExperimentDropdown />
    </div>
    </>
}

export default Home;
import React from "react";
import PersistentMiniSideBar from "../components/Tasks/PersistentMiniSideBar";
import './Home.css';

const Home = () => {
    return<>
    <div className="home-container">
        <PersistentMiniSideBar />
    </div>
    </>
}

export default Home;
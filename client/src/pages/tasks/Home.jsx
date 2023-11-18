import { Box, CssBaseline } from "@mui/material";
import React from "react";
import PersistentMiniSideBar from '../../components/Tasks/PersistentMiniSideBar';
import './Home.css';


const Home = () => {
    return<>
    {/* <div className="home-container"> */}
    <Box sx={{display: 'flex'}}>
        <CssBaseline />
        <PersistentMiniSideBar />
    </Box>

    {/* </div> */}
    </>
}

export default Home;
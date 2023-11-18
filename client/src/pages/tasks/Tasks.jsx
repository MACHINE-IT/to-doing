import { Box, CssBaseline } from "@mui/material";
import React from "react";
import PersistentMiniSideBar from '../../components/Tasks/PersistentMiniSideBar';

const Tasks = () => {
  return (
    <>
      <Box sx={{display: 'flex'}}>
        <CssBaseline />
        <PersistentMiniSideBar />
      </Box>
    </>
  )
}

export default Tasks

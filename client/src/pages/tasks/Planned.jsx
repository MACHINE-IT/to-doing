import { Box, CssBaseline } from "@mui/material";
import React from "react";
import PersistentMiniSideBar from '../../components/Tasks/PersistentMiniSideBar';
//"#e0f7fa"

const Planned = () => {
  return (
    <Box sx={{display: 'flex'}}>
        <CssBaseline />
        <PersistentMiniSideBar />
    </Box>
  )
}

export default Planned

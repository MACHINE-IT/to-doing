import { Box, CssBaseline, Typography, styled } from "@mui/material";
import React, { useState } from "react";
import { BsStar } from "react-icons/bs";
import Navbar from "../../components/Navbar";
import FormSideBar from "../../components/Tasks/FormSideBar";
import TasksView from "../../components/Tasks/TasksView";


const DrawerHeader = styled('div')(({ theme }) => ({
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'flex-end',
  padding: theme.spacing(0, 1),
  // necessary for content to be below app bar
  ...theme.mixins.toolbar,
}));


const Important = () => {

  const [open, setOpen] = React.useState(false);
  const [openRightDrawer, setOpenRightDrawer] = useState(false);

  const handleDrawerOpen = () => {
    setOpen(true);
  };

  const handleDrawerClose = () => {
    setOpen(false);
  };

  const handleCloseRightDrawer = () => {
    setOpenRightDrawer(false);
  }

  const handleOpenRightDrawer = () => {
    setOpenRightDrawer(true);
  }

  return (
      <>
      <Box sx={{display: 'flex'}}>
            <CssBaseline />
        <Navbar open={open} handleDrawerOpen={handleDrawerOpen} 
          handleDrawerClose={handleDrawerClose}></Navbar>
        <Box component="main" sx={{ p: 3, backgroundColor: '#4db6ac'}}>
          <DrawerHeader />
          <Typography paragraph>
            <TasksView HeadlineIcon={<BsStar fontSize={26}></BsStar>} HeadlineName={"Important"} handleOpenRightDrawer = {handleOpenRightDrawer} />
          </Typography>
          <Typography paragraph>
              my-day
          </Typography>
        </Box>

        <FormSideBar handleCloseRightDrawer={handleCloseRightDrawer} openRightDrawer={openRightDrawer}/>
          
      </Box>
  </>
  )
}

export default Important

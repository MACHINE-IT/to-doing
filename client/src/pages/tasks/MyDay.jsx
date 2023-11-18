import { Box, CssBaseline, Typography, styled, useTheme } from "@mui/material";
import React, { useContext, useState } from 'react';
import { BsSun } from "react-icons/bs";
import Navbar from "../../components/Navbar";
import FormSideBar from "../../components/Tasks/FormSideBar";
import TasksView from "../../components/Tasks/TasksView";
import TaskContext from "../../context/Context";
import PageContextProvider from "../../context/PageContextProvider";


const DrawerHeader = styled('div')(({ theme }) => ({
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'flex-end',
  padding: theme.spacing(0, 1),
  // necessary for content to be below app bar
  ...theme.mixins.toolbar,
}));

const MyDay = () => {
  const {tasks, currentPage} = useContext(TaskContext);
  // const {selectedTask, setSelectedTask} = useContext(PageContext);
  const theme = useTheme();
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

  // function selectedTask(task) {
  //   setOpenRightDrawer(Boolean(selectedTask))
  // }

  return (
    <>
      <PageContextProvider>
        <Box sx={{display: 'flex'}}>
              <CssBaseline />
          <Navbar open={open} handleDrawerOpen={handleDrawerOpen} 
            handleDrawerClose={handleDrawerClose}></Navbar>
          <Box component="main" sx={{ padding: 1, width: "100%", minHeight: "100vh",backgroundColor: '#4db6ac'}}>
            <DrawerHeader />
            <Typography paragraph>
              <TasksView taskData HeadlineIcon={<BsSun fontSize={26}></BsSun>} HeadlineName={"My Day"} handleOpenRightDrawer = {handleOpenRightDrawer} />
            </Typography>
            <Typography paragraph>
                {currentPage}
            </Typography>
          </Box>
          <FormSideBar 
          // selectedTask={selectedTask}
           handleCloseRightDrawer={handleCloseRightDrawer} openRightDrawer={openRightDrawer}/>
        </Box>
      </PageContextProvider>
    </>
  )
}

export default MyDay

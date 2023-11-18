import { Box, Grid, styled } from "@mui/material";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { DemoContainer } from '@mui/x-date-pickers/internals/demo';
import React from "react";
// import DateMenuItemData from "../data/DateMenuItemData";
import { CreateTaskForm } from './CreateTaskForm';
import { Headline } from './Headline';
// import SimpleMenu from "./Menu";
import { useContext } from "react";
import TaskContext from "../../context/Context";
import { TaskCards } from './TaskCards';


const BoxContainer = styled(Box)({
    width: 200,
    height: 200,
    display: "flex",
    flexWrap: 'wrap',
})


 const TasksView = ({
    // handleOpenRightDrawer,
     HeadlineName, HeadlineIcon}) => {

    const {tasks} = useContext(TaskContext);




    const handleClick = (event) => {
        console.log(event.currentTarget.parentNode.parentNode.parentNode);
      setAnchorEl(event.currentTarget.parentNode);
    };
    const handleCloseMenu = () => {
      setAnchorEl(null);
    };



    return (
        <>
        <Grid spacing={1}>
                <Box sx={{display: 'flex', overflowY: 'scroll', overflowX: 'hidden', gap: "3px", marginTop: -3, maxHeight: "82vh", flexWrap: 'wrap'}}>
                    <Headline HeadlineIcon={HeadlineIcon} HeadlineName={HeadlineName}  />
                    {
                        tasks.map((eachCard, index) => (
                            <TaskCards key={index} taskData={eachCard} onClick={()=>handleSelectedTask(eachCard)} 
                            // handleOpenRightDrawer={handleOpenRightDrawer} 
                             />
                        ))
                    }
                    {/* <CreateTaskForm  handleClick={handleClick} /> */}
                </Box>
                <Box>
                    <CreateTaskForm  handleClick={handleClick} />
                </Box>
                {
                    false &&                 
                    <LocalizationProvider dateAdapter={AdapterDayjs}>
                        <DemoContainer components={['DatePicker']}>
                        <DatePicker label="Basic date picker" />
                        </DemoContainer>
                    </LocalizationProvider>
                }
        </Grid>
        </>
    )
}

  export default TasksView;
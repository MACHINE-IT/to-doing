import {
  Box,
  IconButton, Input,
  Paper,
  Typography
} from "@mui/material";
import React, { useContext, useState } from "react";
import { BsPlusLg } from "react-icons/bs";
import { GiAlarmClock } from "react-icons/gi";
import { MdOutlineEventRepeat } from "react-icons/md";
import { SlCalender } from "react-icons/sl";
import TaskContext from "../../context/Context";
import PageContext from "../../context/PageContext";
import createTask from "../../model/Task";
import AlarmOptionMenu from "./AlarmOptionMenu";
import DateAndTimePicker from "./DateAndTimePicker";
import CalendarButton from "./DatePicker";
import DueDateOptionMenu from './DueDateOptionMenu';
import TaskRepeatOptionMenu from "./TaskRepeatOptionMenu";

export function CreateTaskForm({ handleClick }) {

  
  const {addTask, removeTask, tasks} = useContext(TaskContext);

  const {dueDateFromOption, setDueDateMenuAnchorEl} = useContext(PageContext);
  const {alarmMenuOptionValue, setAlarmMenuOptionAnchorEl} = useContext(PageContext);
  const {taskRepeatMenuOptionValue, setTaskRepeatMenuOptionAnchorEl} = useContext(PageContext);

  const [taskTitle, setTaskTitle] = useState("");
  const [dueDate, setDueDate] = useState(new Date());
  const [recurring, setRecurring] = useState(null);
  const [reminders, setReminders] = useState(null);
  const [anchorEl, setAnchorEl] = useState(null);
  
  const [openModal, setOpenModal] = React.useState(false);

  const handleMenuClickForDueDateSelection = (event) => {
    console.log("jscs", event.currentTarget);
    setDueDateMenuAnchorEl(event.currentTarget);
  };

  const handleMenuClickForAlarmOptionMenu = (event) => {
    setAlarmMenuOptionAnchorEl(event.currentTarget);
  }

  const handleMenuClickForTaskRepeatOptionMenu = (event) => {
    setTaskRepeatMenuOptionAnchorEl(event.currentTarget);
  }




  const handleSubmit = () => {
    console.log("tasks after", tasks);
    addTask(createTask(
      {
        id: 1,
        title: taskTitle,
        note: "df",
        completed: false,
        due: new Date().getTime(),
        isFavourite: false,
        steps: null
      }
    ));
    console.log("tasks after", tasks);
  }
  
  // ...state and functions...

  return (
    <Box sx={{ background: '#4db6ac', height: "70px", width: '100%', position: 'fixed', bottom: 0, left: "60px", right: 0, zIndex: 100 }}>
      <DueDateOptionMenu />
      <AlarmOptionMenu />
      <TaskRepeatOptionMenu />
      <CalendarButton></CalendarButton>
      <DateAndTimePicker />
      <Paper sx={{
        display: 'flex',
        justifyContent: 'space-between',
        alignItems: 'flex-start',
        height: "100%",
        padding: '10px',
        borderRadius: 0, // you can apply a radius if you want rounded corners
        backgroundColor: 'rgba(255, 255, 255, 0.95)' // slightly transparent white
      }}>
        <Paper
      sx={{
        display: "flex",
        flexDirection: "row",
        // position: "fixed",
        bottom: 15,
        width: "90%",
      }}
    >
      <Box
        flex={0.5}
        sx={{
          background: "none",
          border: "none",
        }}
      >
        <IconButton disableRipple>
          <BsPlusLg></BsPlusLg>
        </IconButton>
      </Box>
      <Box
        flex={9}
        sx={{
          background: "none",
        }}
        display="flex"
        flexDirection="row"
      >
        <Input
          onChange={(e) => setTaskTitle(e.target.value)}
          placeholder="Add tasks"
          sx={{
            border: "none",
            boxShadow: "none",
          }}
          
        ></Input>
        <IconButton onClick={handleSubmit} fontSize="14px">Add</IconButton>
      </Box>
      <Box>
        <IconButton sx={{borderRadius:0}} onClick={(e) =>handleMenuClickForDueDateSelection(e)}>
          <SlCalender></SlCalender>
          <Typography sx={{paddingLeft: '3px'}}>{dueDateFromOption !== null ? dueDateFromOption.title : ""}</Typography>
        </IconButton>
        <IconButton onClick={(e) => handleMenuClickForAlarmOptionMenu(e)} sx={{borderRadius:0}}>
          <GiAlarmClock sx={{paddingLeft: '3px'}}>{alarmMenuOptionValue}</GiAlarmClock>
        </IconButton>
        <IconButton onClick={(e) => handleMenuClickForTaskRepeatOptionMenu(e)} sx={{borderRadius:0}}>
          <MdOutlineEventRepeat>{taskRepeatMenuOptionValue}</MdOutlineEventRepeat>
        </IconButton>
      </Box>
    </Paper>
        {/* Your icons and input field */}
        {/* <Typography>asdfjas;dfjas;kldfj</Typography> */}
      </Paper>
    </Box>
  );
}

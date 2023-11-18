
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import React, { useContext } from "react";
import PageContext from "../../context/PageContext";
import AlarmMenuOptionData from "../data/AlarmMenuOptionData";


const TaskRepeatOptionMenu = ({}) => {

  const {setAlarmMenuOptionValue, alarmMenuOptionAnchorEl, setAlarmMenuOptionAnchorEl } = useContext(PageContext);

  const {setTaskRepeatMenuOptionValue, taskRepeatMenuOptionAnchorEl, setTaskRepeatMenuOptionAnchorEl} = useContext(PageContext);
  const handleClose = (item) => {
    setTaskRepeatMenuOptionAnchorEl(null); // Always close the menu
    if (item !== undefined) {
      setTaskRepeatMenuOptionValue(item.title);
    }
  }

  return (

    <Menu
      id="simple-menu"
      anchorEl={taskRepeatMenuOptionAnchorEl}  
      keepMounted
      open={Boolean(taskRepeatMenuOptionAnchorEl)}
      onClose={() => handleClose()}
      style={{ position: "absolute", left: "-30px",top: "-55px" }}  
    >

        {
        AlarmMenuOptionData.map((item, index) => (
          <MenuItem key={index} onClick={() => handleClose(item)}>
            {/* <ListItemIcon> // has to uncomment soon
              { item.IconElement }
            </ListItemIcon> */}
            {item.title}
          </MenuItem>
        ))
        }
    </Menu> // </div>
  );
};

export default TaskRepeatOptionMenu;

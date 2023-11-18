import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import React, { useContext } from "react";
import PageContext from "../../context/PageContext";
import AlarmMenuOptionData from "../data/AlarmMenuOptionData";


const AlarmOptionMenu = ({}) => {

  const {setAlarmMenuOptionValue, setAlarmDateTimePickerOpen, alarmMenuOptionAnchorEl, setAlarmMenuOptionAnchorEl } = useContext(PageContext);

  const handleClose = (item) => {
    setAlarmMenuOptionAnchorEl(null); // Always close the menu
    if(item != undefined && item.title === "Pick a date & time") {
      setAlarmDateTimePickerOpen(true);
    }
    else if (item !== undefined) {
      setAlarmMenuOptionValue(item.title);
    }
  }

  // const handleMenuClickForAlarmDatePickerMenu = (e) => {
  //   setAlarmDateTimePickerOpen(true);
  // }

  return (

    <Menu
      id="simple-menu"
      anchorEl={alarmMenuOptionAnchorEl}  
      keepMounted
      open={Boolean(alarmMenuOptionAnchorEl)}
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

export default AlarmOptionMenu;

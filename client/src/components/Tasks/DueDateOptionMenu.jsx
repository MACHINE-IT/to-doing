import { ListItemIcon } from "@mui/material";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import React, { useContext } from "react";
import PageContext from "../../context/PageContext";
import DueDateMenuItemData from '../data/DueDateMenuItemData';

const DueDateOptionMenu = ({}) => {

  const {dueDateFromOption, setDueDateFromOption, setDatePickerOpen, dueDateMenuAnchorEl, setDueDateMenuAnchorEl} = useContext(PageContext);

  // const classes = useStyles();

  const handleClose = (item) => {
    setDueDateMenuAnchorEl(null); // Always close the menu
    setTimeout(() => {
      if (item !== undefined && item.title === "Pick a date") {
        //open the date picker.
        // setDueDateFromOption(item);
        console.log("clicked");
        setDatePickerOpen(true); 
      } else if (item !== undefined && item.title === "Remove due date") {
        setDueDateFromOption(null);
      } else if(item !== undefined) {
        setDueDateFromOption(item);
      }
    }, 200);
  }

  const handleCalendarOpen = () => {
    // setAnchorEl(inputRef.current); // Anchor the DatePicker to the input field
// Toggle the open state
  };


  return (

    <Menu
      id="simple-menu"
      anchorEl={dueDateMenuAnchorEl}  
      keepMounted
      open={Boolean(dueDateMenuAnchorEl)}
      onClose={() => handleClose()}
      style={{ position: "absolute", left: "-30px",top: "-55px" }}  
    >
        {
          DueDateMenuItemData.map((item, index) => {
            const isLastItem = index === DueDateMenuItemData.length - 1;
            
            if(!isLastItem || (isLastItem && dueDateFromOption)) {
              return (
                <MenuItem key={index} onClick={() => handleClose(item)}>
                <ListItemIcon>
                  { item.iconElement }
                </ListItemIcon>
                {item.title && <span style={{width: "90px"}} >{item.title}</span>}
                {/* {item.title} */}
                {item.day && <span style={{marginLeft: '10px'}} >{item.day}</span>}
              </MenuItem>
              )
            }
          })
        }
    </Menu> // </div>
  );
};

export default DueDateOptionMenu;

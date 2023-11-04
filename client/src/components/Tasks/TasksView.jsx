import { IconButton } from "@material-ui/core";
import { Edit } from "@material-ui/icons";
import { Delete } from "@mui/icons-material";
import CalendarTodayIcon from '@mui/icons-material/CalendarToday';
import { Box, Card, Chip, Grid, Input, Menu, MenuItem, Paper, Typography, styled } from "@mui/material";
// import { DateCalendar } from '@mui/x-date-pickers/DateCalendar';
import React, { useState } from "react";
import { BsCheck2Circle, BsFlagFill, BsPlusLg } from 'react-icons/bs';
import { GiAlarmClock } from 'react-icons/gi';
import { MdOutlineEventRepeat } from 'react-icons/md';
import { SlCalender } from 'react-icons/sl';
import FormDialog from "./FormDialog";



const BoxContainer = styled(Box)({
    width: 200,
    height: 200,
    display: "flex",
    flexWrap: 'wrap',
    // border: 'black',
    // borderStyle: 'solid'
})


 const TasksView = () => {

    const [date, setDate] = useState(new Date());
    const cards = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13];

    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
      setOpen(true);
    };
  
    const handleClose = () => {
      setOpen(false);
    };

    const [anchorEl, setAnchorEl] = React.useState(null);
    const openDropdown = Boolean(anchorEl);
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
                    {/* <Paper sx={{display: 'flex', flexDirection: 'row',}}>
                        <Box flex={3}>
                            <Button>Todo</Button>
                            <Button>Completed</Button>
                        </Box>
                        <Box sx={{flex: 1, display: 'flex', flexDirection: 'row'}}>
                            <Button>Filter</Button>
                            <Button variant="contained" color="primary" onClick={handleClickOpen}>Add</Button>
                        </Box>
                    </Paper> */}
                <Box sx={{display: 'flex', gap: 1, flexWrap: 'wrap'}}>
                    {
                        cards.map(eachCard => (
                        <Card  sx={{width: 400, height: 'auto', marginTop: 1, padding: 1, display: 'flex'}}>
                            {/* <Box sx={{display: "flex", alignItems: 'center'}}>

                            </Box> */}
                        <Box sx={{width: '100%'}}>
                            <Box sx={{display: "flex", justifyContent: 'space-between', alignItems: 'center', width: '100%'}}>
                                <IconButton style={{padding: '6px'}}>
                                    <BsCheck2Circle  style={{  fontSize: "20px", marginTop: "2px", marginLeft: '10px'}} ></BsCheck2Circle>
                                </IconButton>
                                <Box sx={{flex: 2, display:'flex', alignItems: 'center', justifyContent: 'space-between'}}>
                                    <Typography  justifySelf='flex-start' variant="p" sx={{fontSize:'1rem', color: 'grey'}} >Task Master</Typography>
                                    <Chip label='low' color='warning'></Chip>
                                </Box>
                            </Box>
                                <Box sx={{paddingLeft: 5}}>
                                    <Box sx={{display:'flex' ,justifyContent:'center', alignItems: 'center'}}>
                                        <CalendarTodayIcon sx={{fontSize:17, cursor: 'pointer', color:'green'}} fontSize="small" />
                                        <Typography variant="p" color='green' fontSize="10px">Today</Typography>
                                    </Box>
                                    <IconButton >
                                        <Delete sx={{fontSize: 18, color: 'red'}} ></Delete>
                                    </IconButton>
                                    <IconButton>
                                        <Edit style={{fontSize:"17px"}}></Edit>
                                    </IconButton>
                                    <IconButton>
                                        <BsFlagFill fontSize="15px" color="red" /> 
                                    </IconButton>
                                </Box>
                        </Box>
                        <Box>
                            <Box></Box>
                        </Box>
                        </Card>
                        ))
                    }

                        <Menu
                            id="basic-menu"
                            anchorEl={anchorEl}
                            open={openDropdown}
                            onClose={handleCloseMenu}
                            MenuListProps={{
                                'aria-labelledby': 'basic-button',
                            }}
                            >
                            <MenuItem onClick={handleCloseMenu} disableRipple={true} > Profile</MenuItem>
                            <MenuItem onClick={handleCloseMenu}>My account</MenuItem>
                            <MenuItem onClick={handleCloseMenu}>Logout</MenuItem>
                        </Menu>
                        {/* <DateCalendar /> */}
                    <Paper sx={{display: 'flex', flexDirection: 'row', position: 'fixed', bottom: 15, width: "80%"}}>
                        {/* <Box flex={3}>
                            <Button>Todo</Button>
                            <Button>Completed</Button>
                        </Box>
                        <Box sx={{flex: 1, display: 'flex', flexDirection: 'row'}}>
                            <Button>Filter</Button>
                            <Button variant="contained" color="primary" onClick={handleClickOpen}>Add</Button>
                        </Box> */}
                        <Box flex={.5} sx={{background: 'none', border: 'none'}}>
                            <IconButton  disableRipple>
                                <BsPlusLg></BsPlusLg>
                            </IconButton>
                        </Box>
                        <Box flex={9} sx={{background: 'none'}}>
                            <Input placeholder="Add tasks" sx={{ border: "none", boxShadow: 'none'}}>
                            </Input>
                        </Box>
                        <Box>

                            <IconButton onClick={handleClick}>
                                <SlCalender></SlCalender>
                            </IconButton>
                            <IconButton><GiAlarmClock></GiAlarmClock></IconButton>
                            <IconButton><MdOutlineEventRepeat></MdOutlineEventRepeat></IconButton>
                        </Box>
                    </Paper>
                </Box>
                
                <FormDialog open={open} handleClose={handleClose}/>

        </Grid>
        </>
    )
}

export default TasksView;
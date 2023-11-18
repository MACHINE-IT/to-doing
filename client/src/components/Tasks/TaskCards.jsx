import { Box, Card, Checkbox, IconButton, Typography } from "@mui/material";
import React, { useContext } from "react";
import { AiOutlineCalendar } from "react-icons/ai";
import { BsArrowRepeat, BsFillCalendar2EventFill, BsStar } from "react-icons/bs";
import PageContext from "../../context/PageContext";

export function TaskCards({ taskData }) {
  const { setSelectedTask } = useContext(PageContext);

  return (
    <Card
      onClick={() => setSelectedTask(taskData)}
      sx={{
        '&:hover': {
          backgroundColor: '#fafafa'
        },
         transition: 'background-color 0.1s',
        width: '100%',
        height: 'fit-content',
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'flex-start',
        justifyContent: 'space-between',
        // padding: 1,
        backgroundColor: '#F5F5F5',
        boxShadow: '0 2px 4px rgba(0,0,0,0.1)', // subtle shadow
        borderRadius: '4px', // slightly rounded corners
        // marginBottom: '2px', // space between cards
        ':hover': {
          boxShadow: '0 4px 8px rgba(0,0,0,0.1)', // more pronounced shadow on hover
        },
      }}
    >
      <Box sx={{ display: 'flex', width: '100%', alignItems: 'center' }}>
        <Checkbox /> {/* Replace this with your custom checkbox style */}
        <Typography  variant="h6" noWrap sx={{ flexGrow: 1, textAlign: 'start' }}>
          {taskData.title}
        </Typography>
        <Box sx={{ display: 'flex', alignItems: 'center'}}>
          <IconButton>
            <BsStar />
          </IconButton>
        </Box>
      </Box>
      <Box sx={{ display: 'flex', alignItems: 'center'}}>
        {/* Add date and other icons here */}
        <Checkbox sx={{display: 'none', marginRight: 0, paddingRight:0}}/>
        <Box sx={{ marginTop: '-8px', marginLeft: '46.9937px',display: 'flex', alignItems: 'center' }}>
          <Typography variant="body2" sx={{ marginRight: '8px' }}>
            0 of 3
          </Typography>
          <Typography variant="caption" fontSize={'small'} color="text.secondary" sx={{ mx: 1 }}>
            &bull;
          </Typography>
          <AiOutlineCalendar />
          <BsArrowRepeat style={{ marginLeft: '8px', transform: 'rotate(-30deg)' }} />
          {/* Add more icons here if needed */}
          <BsFillCalendar2EventFill style={{ marginLeft: '8px',marginRight: '8px' }} />
          <Typography variant="caption" sx={{ color: 'text.secondary' }}>
            Sat, 4 Nov
          </Typography>
        </Box>
        
        {/* You can add additional icons or text here */}
      </Box>
    </Card>
  );
}

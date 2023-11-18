// import { Event } from '@material-ui/icons';
import { Box } from '@mui/material';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import React, { useContext, useState } from 'react';
import PageContext from '../../context/PageContext';
// import './DatePicker.css';


const CalendarButton = () => {
  const [selectedDate, setSelectedDate] = useState(null);
  // const [open, setOpen] = useState(false); // State to track open/close of the calendar
  const {datePickerOpen, setDatePickerOpen, dueDateFromOption, setDueDateFromOption} = useContext(PageContext);

  const days = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
  const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];
  // const [anchorEl, setAnchorEl] = useState(null); // State for anchoring the DatePicker
  // const inputRef = useRef(null);
  const handleDateChange = (date) => {
    console.log(date);
    setSelectedDate(date);
    setDueDateFromOption({
      "title": `${days[date.day()]}, ${date.date()} ${months[date.month()]}`,
      "date": date,
      "iconElement": null,
      "day": null
    });
    // setDueDateFromOption(date)
  };


  const formatDate = (date) => {
    const options = { weekday: 'short', day: 'numeric', month: 'short' };
    return date.toLocaleDateString('en-US', options);
  };

  const displayDate = selectedDate 
  ? `${days[selectedDate.day()]}, ${selectedDate.date()} ${months[selectedDate.month()]}`
  : "Select a Date";
  return (
        <Box>
        {datePickerOpen && (
          <LocalizationProvider dateAdapter={AdapterDayjs}>
            <DatePicker 
              // anchorEl={anchorEl} // Set the anchor element
              sx={{ '& :last-child': { display: 'none' } }}

              open={datePickerOpen}
              value={selectedDate}
              onChange={handleDateChange}
              onClose={() => setDatePickerOpen(false)}
              renderInput={({  inputProps }) => (
                <input  {...inputProps} style={{ display: 'none' }} />
              )}
            />
          </LocalizationProvider>
        )}
        {/* <IconButton onClick={handleCalendarOpen} sx={{ borderRadius: 0 }}>
          <EventIcon />
          <Typography>{displayDate}</Typography>
        </IconButton> */}
      </Box>
  );
};

export default CalendarButton;

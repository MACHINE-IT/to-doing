import { renderTimeViewClock } from '@mui/x-date-pickers';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DateTimePicker } from '@mui/x-date-pickers/DateTimePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import dayjs from 'dayjs';
import * as React from 'react';
import { useContext, useState } from 'react';
import PageContext from '../../context/PageContext';


export default function DateAndTimePicker() {

  const [value, setValue] = useState(dayjs('2022-04-17T15:30'));
  const [open, setOpen] = useState(false);

  const {alarmDateTimePickerOpen, setAlarmDateTimePickerOpen, setAlarmMenuOptionValue} = useContext(PageContext);
  


  return (
    <LocalizationProvider dateAdapter={AdapterDayjs}>
             <DateTimePicker
        label="With Time Clock"
          open={alarmDateTimePickerOpen}
          onClose={() => setAlarmDateTimePickerOpen(false)}
          sx={{display: "none"}}
          onChange={(newValue) => setAlarmMenuOptionValue(
            {
              "title": "Picked some date and time",
              "IconElement": null,
              "dateAndTime": newValue
           }
          )}
          viewRenderers={{
            hours: renderTimeViewClock,
            minutes: renderTimeViewClock,
            seconds: renderTimeViewClock,
          }}
          // PopperProps={{
          //   sx: {
          //     position: 'fixed', 
          //     top: '50%', 
          //     left: '50%', 
          //     transform: 'translate(-50%, -50%)'
          //   }
          // }}
        />
    </LocalizationProvider>
  );
}
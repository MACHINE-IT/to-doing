// // import { Box, TextField } from '@mui/material'
// import { Button } from '@mui/material';
// import React from 'react';
// import BasicDateTimePicker from '../../components/Tasks/DateAndTimePicker';
// // import SimpleMenu from '../../components/Tasks/Menu'
// // import DateMenuItemData from '../../components/data/DateMenuItemData'
// import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
// import { DateTimePicker } from '@mui/x-date-pickers/DateTimePicker';
// import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
// import { DemoContainer } from '@mui/x-date-pickers/internals/demo';
// import dayjs from 'dayjs';


// const Testing = () => {
//   const [value, setValue] = React.useState<Dayjs | null>(dayjs('2022-04-17T15:30'));

//   return (
//     <div>
//       <div>
//         {/* <BasicDateTimePicker></BasicDateTimePicker> */}
//          <BasicDateTimePicker>sdfsd</BasicDateTimePicker>
//          <LocalizationProvider dateAdapter={AdapterDayjs}>
//           <DemoContainer components={['DateTimePicker', 'DateTimePicker']}>
//             <DateTimePicker
//               label="Uncontrolled picker"
//               defaultValue={dayjs('2022-04-17T15:30')}
//             />
//             <DateTimePicker
//               label="Controlled picker"
//               value={value}
//               onChange={(newValue) => setValue(newValue)}
//             />
//           </DemoContainer>
//         </LocalizationProvider>
//          <Button>sdf</Button>
//         {/* <CalenderButton></CalenderButton> */}
//         {/* <SimpleMenu MenuItemData={
//             // [{"title": "ssdfome"}]
//             // DrawerItemData
//             DateMenuItemData
//             }></SimpleMenu> */}
//             {/* <Box
//       component="form"
//       sx={{
//         '& > :not(style)': { m: 1, width: '25ch' },
//       }}
//       noValidate
//       autoComplete="off"
//     >
//       <TextField id="outlined-basic" label="Outlined" variant="outlined" />
//       <TextField id="filled-basic" label="Filled" variant="filled" />
//       <TextField sx={{backgroundColor: 'none'}} id="standard-basic" label="Standard" variant="standard" />
//     </Box> */}
//       </div>
//     </div>
//   )
// }

// export default Testing

import { Button, TextField } from '@mui/material';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DateTimePicker } from '@mui/x-date-pickers/DateTimePicker';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { renderTimeViewClock } from '@mui/x-date-pickers/timeViewRenderers';
import dayjs from 'dayjs';
import React, { useState } from 'react';


const Testing = () => {
  const [value, setValue] = useState(dayjs('2022-04-17T15:30'));
  const [open, setOpen] = useState(false);
  
  const handle = (e) => {
    setOpen(true);
  }

  return (
    <div style={{marginTop: "500px"}}>
      <LocalizationProvider  dateAdapter={AdapterDayjs}>
        {/* Uncontrolled DateTimePicker */}
        <DateTimePicker
        label="With Time Clock"
          open={open}
          onClose={() => setOpen(false)}
          sx={{display: "none"}}
          onChange={(newValue) => setValue(newValue)}
          viewRenderers={{
            hours: renderTimeViewClock,
            minutes: renderTimeViewClock,
            seconds: renderTimeViewClock,
          }}
          PopperProps={{
            sx: {
              position: 'fixed', 
              top: '50%', 
              left: '50%', 
              transform: 'translate(-50%, -50%)'
            }
          }}
        />

        {/* Controlled DateTimePicker */}
        <DateTimePicker
          sx={{':last-child': {display: 'none'}}}
          label="Controlled picker"
          value={value}
          onChange={(newValue) => setValue(newValue)}
          renderInput={(props) => <TextField {...props} />}
        />
      </LocalizationProvider>

      {/* Basic Button Example */}
      <Button onClick={(e) => handle(e)}>{value.$M}Example Button</Button>
    </div>
  );
}

export default Testing;

import React, { useState } from 'react';
import PageContext from './PageContext';

const PageContextProvider = ({children}) => {
  // Task-related states
  const [selectedTask, setSelectedTask] = useState(null);

  // DueDates-related states
  const [dueDateFromOption, setDueDateFromOption] = useState(null);
  const [dueDateMenuAnchorEl, setDueDateMenuAnchorEl] = useState(false);
  const [datePickerOpen, setDatePickerOpen] = useState(false);

  
  // Alarm-related states
  const [alarmMenuOptionValue, setAlarmMenuOptionValue] = useState(null);
  const [alarmMenuOptionAnchorEl, setAlarmMenuOptionAnchorEl] = useState(false);
  const [alarmDateTimePickerOpen, setAlarmDateTimePickerOpen] = useState(false);

  // Recurring-related states
  const [taskRepeatMenuOptionValue, setTaskRepeatMenuOptionValue] = useState(null);
  const [taskRepeatMenuOptionAnchorEl, setTaskRepeatMenuOptionAnchorEl] = useState(false);
  const [taskRepeatCustomMenuOptionOpen, setTaskRepeatCustomMenuOptionOpen] = useState(false);

  return (
    <PageContext.Provider
      value={{
        selectedTask, setSelectedTask,

        dueDateFromOption, setDueDateFromOption,
        setDueDateMenuAnchorEl, dueDateMenuAnchorEl,
        datePickerOpen, setDatePickerOpen,

        alarmMenuOptionValue, setAlarmMenuOptionValue,
        alarmMenuOptionAnchorEl, setAlarmMenuOptionAnchorEl,
        alarmDateTimePickerOpen, setAlarmDateTimePickerOpen,

        taskRepeatMenuOptionValue, setTaskRepeatMenuOptionValue,
        taskRepeatMenuOptionAnchorEl, setTaskRepeatMenuOptionAnchorEl,
        taskRepeatCustomMenuOptionOpen, setTaskRepeatCustomMenuOptionOpen
        
      }}
    >
      {children}
    </PageContext.Provider>
  );
}

export default PageContextProvider

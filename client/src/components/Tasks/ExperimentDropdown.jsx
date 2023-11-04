import * as React from 'react';
import { Dropdown } from '@mui/base/Dropdown';
import { Menu } from '@mui/base/Menu';
import { MenuButton as BaseMenuButton } from '@mui/base/MenuButton';
import { MenuItem as BaseMenuItem, menuItemClasses } from '@mui/base/MenuItem';
import List from '@mui/material/List';
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemIcon from '@mui/material/ListItemIcon';
import ListItemText from '@mui/material/ListItemText';
import InboxIcon from '@mui/icons-material/Inbox';
import Button from '@mui/material/Button';
import Box from '@mui/material/Box';
import Card from '@mui/material/Card';
import FormGroup from '@mui/material/FormGroup';
import FormControlLabel from '@mui/material/FormControlLabel';
import Checkbox from '@mui/material/Checkbox';
import {useEffect, useState} from 'react';
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import { styled } from '@mui/system';
import DeleteIcon from '@mui/icons-material/Delete';
import SendIcon from '@mui/icons-material/Send';
import Stack from '@mui/material/Stack';
import './Demo.css';

export default function MenuIntroduction() {

  const tasksList = [
    {
      taskId: 'jeqk654',
      title: 'Task jeqk654',
      status: 'IN_PROGRESS',
      priority: 'LOW',
    },
    {
      taskId: 'zcesku',
      title: 'Task zcesku',
      status: 'CANCELLED',
      priority: 'LOW',
    },
    {
      taskId: 'r5ajz',
      title: 'Task r5ajz',
      status: 'CANCELLED',
      priority: 'HIGH',
    },
    {
      taskId: 'xa7h6s',
      title: 'Task xa7h6s',
      status: 'TODO',
      priority: 'HIGH',
    },
    {
      taskId: 'j8mo6v',
      title: 'Task j8mo6v',
      status: 'DONE',
      priority: 'MEDIUM',
    },
    {
      taskId: 'sdcyyj',
      title: 'Task sdcyyj',
      status: 'CANCELLED',
      priority: 'MEDIUM',
    },
    {
      taskId: '3sgp7r',
      title: 'Task 3sgp7r',
      status: 'BACKLOG',
      priority: 'MEDIUM',
    },
    {
      taskId: 'pz3tmw',
      title: 'Task pz3tmw',
      status: 'TODO',
      priority: 'HIGH',
    },
    {
      taskId: '05gi8t',
      title: 'Task 05gi8t',
      status: 'CANCELLED',
      priority: 'MEDIUM',
    },
    {
      taskId: 'sbfz77x',
      title: 'Task sbfz77x',
      status: 'TODO',
      priority: 'LOW',
    },
    {
      taskId: 'brdnk',
      title: 'Task brdnk',
      status: 'IN_PROGRESS',
      priority: 'MEDIUM',
    },
    {
      taskId: 'u8wo4f',
      title: 'Task u8wo4f',
      status: 'CANCELLED',
      priority: 'MEDIUM',
    },
    {
      taskId: 'j6wol',
      title: 'Task j6wol',
      status: 'CANCELLED',
      priority: 'HIGH',
    },
    {
      taskId: 'nhko2',
      title: 'Task nhko2',
      status: 'BACKLOG',
      priority: 'LOW',
    },
    {
      taskId: 'q9jd2b',
      title: 'Task q9jd2b',
      status: 'IN_PROGRESS',
      priority: 'LOW',
    },
    {
      taskId: 'txny',
      title: 'Task txny',
      status: 'BACKLOG',
      priority: 'HIGH',
    },
    {
      taskId: '1lizoq',
      title: 'Task 1lizoq',
      status: 'DONE',
      priority: 'HIGH',
    },
    {
      taskId: 'k9a6ao',
      title: 'Task k9a6ao',
      status: 'CANCELLED',
      priority: 'MEDIUM',
    },
    {
      taskId: 'meccm5',
      title: 'Task meccm5',
      status: 'DONE',
      priority: 'HIGH',
    },
    {
      taskId: 'p1vm8',
      title: 'Task p1vm8',
      status: 'IN_PROGRESS',
      priority: 'HIGH',
    },
  ];

  const status = {
    TODO: 'TODO',
    IN_PROGRESS: 'IN_PROGRESS',
    DONE: 'DONE',
    BACKLOG: 'BACKLOG',
    CANCELLED: 'CANCELLED'
  };

  const [taskStatusCounts, setTaskStatusCounts] = useState({
    TODO: 0,
    IN_PROGRESS: 0,
    DONE: 0,
    BACKLOG: 0,
    CANCELLED: 0,
  });


  let generateStatusCounts = () => {
    const statusCounts = {
      [status.TODO]: 0,
      [status.IN_PROGRESS]: 0,
      [status.DONE]: 0,
      [status.BACKLOG]: 0,
      [status.CANCELLED]: 0,
    }

    tasksList.forEach((task) => {
      if(task.status in statusCounts) {
        statusCounts[task.status]++;
      }
    })

    setTaskStatusCounts(statusCounts);
  }

  useEffect(() => {
    generateStatusCounts();

  }, []);




  const [tasks, setTasks] = useState(tasksList);


  let convertTaskStatus = () => {

  }


  const createHandleMenuClick = (menuItem) => {
    return () => {
      console.log(`Clicked on ${menuItem}`);
    };
  };

  return (
      <Dropdown>

        <MenuButton className="custom-menu-button">My account</MenuButton>
        <Menu className="custom-menu" disablePadding slots={{ listbox: Listbox }}>
          <List disablePadding>
            <>
              <Stack spacing={2} sx={{ mt: 1 }}>
                <TextField
                    label="Search status"
                    InputProps={{
                      type: 'search',
                    }}
                />
              </Stack>
            </>
            {
              Object.entries(taskStatusCounts).map((status, count) => (
                  <ListItem disablePadding onClick={createHandleMenuClick('Profile')}>
                    <ListItemButton disablePaddding className="list-item-button" disableRipple>
                      <ListItem className="custom-list-item">
                        <Checkbox
                            disablePadding
                            className="custom-checkbox"
                            disableRipple={true}
                            disableTouchRipple={true}
                        />
                      </ListItem>
                      <ListItemIcon className="custom-list-item-icon">o</ListItemIcon>
                      <ListItem className="custom-list-item-label">{status[0]} {status[1]}</ListItem>
                    </ListItemButton>
                  </ListItem>
              ))
            }
            <ListItem disablePadding onClick={createHandleMenuClick('Profile')}>
              <ListItemButton className="list-item-button" disableRipple>
                <ListItem className="custom-list-item">
                  <Checkbox
                      disablePadding
                      disableRipple={true}
                      disableTouchRipple={true}
                  />
                </ListItem>
                <ListItemIcon className="custom-list-item-icon">o</ListItemIcon>
                <ListItem className="custom-list-item-label">name</ListItem>
              </ListItemButton>
            </ListItem>
          </List>

          <MenuItem onClick={createHandleMenuClick('Language settings')}>
            Clear filters
          </MenuItem>
          {/* <MenuItem onClick={createHandleMenuClick('Log out')}>Log out</MenuItem> */}
          <List></List>
        </Menu>
      </Dropdown>
  );
}

const blue = {
  100: '#DAECFF',
  200: '#99CCF3',
  400: '#3399FF',
  500: '#007FFF',
  600: '#0072E5',
  900: '#003A75',
};

const grey = {
  50: '#f6f8fa',
  100: '#eaeef2',
  200: '#d0d7de',
  300: '#afb8c1',
  400: '#8c959f',
  500: '#6e7781',
  600: '#57606a',
  700: '#424a53',
  800: '#32383f',
  900: '#24292f',
};

const Listbox = styled('ul')(
    ({ theme }) => `
  font-family: IBM Plex Sans, sans-serif;
  font-size: 0.875rem;
  box-sizing: border-box;
  padding: 6px;
  margin: 12px 0;
  min-width: 200px;
  border-radius: 12px;
  overflow: auto;
  outline: 0px;
  background: ${theme.palette.mode === 'dark' ? grey[900] : '#fff'};
  border: 1px solid ${theme.palette.mode === 'dark' ? grey[700] : grey[200]};
  color: ${theme.palette.mode === 'dark' ? grey[300] : grey[900]};
  box-shadow: 0px 4px 30px ${
        theme.palette.mode === 'dark' ? grey[900] : grey[200]
    };
  z-index: 1;
  `
);

const MenuItem = styled(BaseMenuItem)(
    ({ theme }) => `
  list-style: none;
  padding: 8px;
  border-radius: 8px;
  cursor: default;
  user-select: none;

  &:last-of-type {
    border-bottom: none;
  }

  &.${menuItemClasses.focusVisible} {
    outline: 3px solid ${theme.palette.mode === 'dark' ? blue[600] : blue[200]};
    background-color: ${theme.palette.mode === 'dark' ? grey[800] : grey[100]};
    color: ${theme.palette.mode === 'dark' ? grey[300] : grey[900]};
  }

  &.${menuItemClasses.disabled} {
    color: ${theme.palette.mode === 'dark' ? grey[700] : grey[400]};
  }

  &:hover:not(.${menuItemClasses.disabled}) {
    background-color: ${theme.palette.mode === 'dark' ? grey[800] : grey[100]};
    color: ${theme.palette.mode === 'dark' ? grey[300] : grey[900]};
  }
  `
);

const MenuButton = styled(BaseMenuButton)(
    ({ theme }) => `
  font-family: IBM Plex Sans, sans-serif;
  font-size: 0.875rem;
  font-weight: 600;
  box-sizing: border-box;
  min-height: calc(1.5em + 22px);
  border-radius: 12px;
  padding: 8px 14px;
  line-height: 1.5;
  background: ${theme.palette.mode === 'dark' ? grey[900] : '#fff'};
  border: 1px solid ${theme.palette.mode === 'dark' ? grey[700] : grey[200]};
  color: ${theme.palette.mode === 'dark' ? grey[300] : grey[900]};

  transition-property: all;
  transition-timing-function: cubic-bezier(0.4, 0, 0.2, 1);
  transition-duration: 120ms;

  &:hover {
    background: ${theme.palette.mode === 'dark' ? grey[800] : grey[50]};
    border-color: ${theme.palette.mode === 'dark' ? grey[600] : grey[300]};
  }

  &:focus-visible {
    border-color: ${blue[400]};
    outline: 3px solid ${theme.palette.mode === 'dark' ? blue[500] : blue[200]};
  }
  `
);

import { Close } from '@mui/icons-material';
import { Box, Divider, IconButton, Input, MenuItem, Paper, Stack, Typography, styled } from '@mui/material';
import React, { useContext } from 'react';
import { BsCircle, BsPlusLg, BsStar, BsSun } from 'react-icons/bs';
import PageContext from '../../context/PageContext';


const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(1),
    textAlign: 'center',
    border: '#c9c9c9 1px',
    borderStyle: 'solid',
    padding: 0,
    color: theme.palette.text.secondary,
    flexGrow: 1,
    flexDirection: 'column',
    margin: '7px'
  }));

const FormSideBar = ({
  // handleCloseRightDrawer,
  //  openRightDrawer
  }) => {


  const {selectedTask, setSelectedTask} = useContext(PageContext);

  const handleCloseRightDrawer = () => {
    setSelectedTask(null);
  }

  return (
    <>
        <Box component="main" sx={{ width: Boolean(selectedTask) ? '60%' : '0%', background: 'white', height: '100px', marginTop: '40px', transition: 'width 0.3s'}}>
            <Box sx={{display: Boolean(selectedTask) ? 'flex' : 'none', justifyContent: 'flex-end'}}>
                <IconButton onClick={handleCloseRightDrawer}>
                    <Close></Close>
                </IconButton>
            </Box>
            <Stack sx={{display: Boolean(selectedTask) ? 'flex': 'none'}}>
              <TaskRenameOption     />
              <TaskAddToMyDayOption     />
              <TaskDateSettingOptions     />
              <AssignToOption />
              <AddFileOption />
            </Stack>
          <Box>
          </Box>
        </Box>
    </>
  )
}

export default FormSideBar

    function AssignToOption() {
      return(
        <Item>
          <MenuItem divider={<Divider orientation='horizontal'></Divider>} display='flex' alignItems='center' flexDirection='row'>
              <Box>
                <BsSun fontSize='15px'></BsSun>
              </Box>
              <Box sx={{
paddingLeft: '10px'
}}>
                <Typography>{"Assign to"}</Typography>
              </Box>
          </MenuItem>
        </Item>
      )
    }

    function AddFileOption() {
      return(
        <Item>
          <MenuItem divider={<Divider orientation='horizontal'></Divider>} display='flex' alignItems='center' flexDirection='row'>
              <Box>
                <BsSun fontSize='15px'></BsSun>
              </Box>
              <Box sx={{
paddingLeft: '10px'
}}>
                <Typography>{"Add file"}</Typography>
              </Box>
          </MenuItem>
        </Item>
      )
    }

    function TaskDateSettingOptions({}) {
      return (<Item>
                  <MenuItem divider={<Divider orientation='horizontal'></Divider>} display='flex' alignItems='center' flexDirection='row'>
                      <Box>
                        <BsSun fontSize='15px'></BsSun>
                      </Box>
                      <Box sx={{
      paddingLeft: '10px'
    }}>
                        <Typography>Remind me at 07:00</Typography>
                      </Box>
                  </MenuItem>
                  <MenuItem divider={<Divider orientation='horizontal'></Divider>} display='flex' alignItems='center' flexDirection='row'>
                      <Box>
                        <BsSun fontSize='15px'></BsSun>
                      </Box>
                      <Box sx={{
      paddingLeft: '10px'
    }}>
                        <Typography>{"Due, " + new Date().getDate()}</Typography>
                      </Box>
                  </MenuItem>
                  <MenuItem divider={<Divider orientation='horizontal'></Divider>} display='flex' alignItems='center' flexDirection='row'>
                      <Box>
                        <BsSun fontSize='15px'></BsSun>
                      </Box>
                      <Box sx={{
      paddingLeft: '10px'
    }}>
                        <Typography>Daily</Typography>
                      </Box>
                  </MenuItem>
                </Item>);
    }
  

    function TaskAddToMyDayOption({}) {
      return (
        <Item>
          <MenuItem
            sx={{
              margin: 0,
              padding: 1,
            }}
            display="flex"
            alignItems="center"
            flexDirection="row"
          >
            <Box>
              <BsSun fontSize="15px"></BsSun>
            </Box>
            <Box
              sx={{
                paddingLeft: "10px",
              }}
            >
              <Typography>Add to My day</Typography>
            </Box>
          </MenuItem>
        </Item>
      );
    }

    function TaskRenameOption({}) {
      const {selectedTask} = useContext(PageContext);
      return (
        <Item elevation={false}>
          <Stack display="flex" flexDirection="column">
            <Box
              display="flex"
              flexDirection="row"
              alignItems="center"
              justifyContent="center"
            >
              <IconButton>
                <BsCircle></BsCircle>
              </IconButton>
              <Input
                disableUnderline
                value={selectedTask !== null ? selectedTask.title: null}
                sx={{
                  border: "none",
                  boxShadow: "none",
                }}
              ></Input>
              <IconButton>
                <BsStar fontSize="15px"></BsStar>
              </IconButton>
            </Box>
            <Box
              display="flex"
              flexDirection="row"
              alignItems="center"
              justifyContent="center"
            >
              <IconButton>
                <BsPlusLg></BsPlusLg>
              </IconButton>
              <Input
                disableUnderline
                placeholder="Add Step"
                sx={{
                  border: "none",
                  boxShadow: "none",
                }}
              ></Input>
            </Box>
          </Stack>
        </Item>
      );
    }
  
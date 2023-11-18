import { AccountCircle, Notifications } from '@material-ui/icons';
import ChevronLeftIcon from '@mui/icons-material/ChevronLeft';
import ChevronRightIcon from '@mui/icons-material/ChevronRight';
import MailIcon from '@mui/icons-material/Mail';
import MenuIcon from '@mui/icons-material/Menu';
import InboxIcon from '@mui/icons-material/MoveToInbox';
import { Divider, Hidden, Input, List, ListItem, ListItemButton, ListItemIcon, ListItemText, MenuItem, styled, useTheme } from '@mui/material';
import MuiAppBar from '@mui/material/AppBar';
import Box from '@mui/material/Box';
import MuiDrawer from '@mui/material/Drawer';
import IconButton from '@mui/material/IconButton';
import Menu from '@mui/material/Menu';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import React from 'react';
import { GoSearch } from 'react-icons/go';
import { useNavigate } from 'react-router-dom';
import { SignOut } from '../services/Auth';
import './Navbar.css';
import './data/DrawerItemData';
import DrawerItemData from './data/DrawerItemData';


const drawerWidth = 240;

const openedMixin = (theme) => ({
  width: drawerWidth,
  transition: theme.transitions.create('width', {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.enteringScreen,
  }),
  overflowX: 'hidden',
});

const closedMixin = (theme) => ({
  transition: theme.transitions.create('width', {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  overflowX: 'hidden',
  width: `calc(${theme.spacing(7)} + 1px)`,
  [theme.breakpoints.up('sm')]: {
    width: `calc(${theme.spacing(8)} + 1px)`,
  },
});

const DrawerHeader = styled('div')(({ theme }) => ({
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'flex-end',
  padding: theme.spacing(0, 1),
  ...theme.mixins.toolbar,
}));

const AppBar = styled(MuiAppBar, {
  shouldForwardProp: (prop) => prop !== 'open',
})(({ theme, open }) => ({
  zIndex: theme.zIndex.drawer + 1,
  transition: theme.transitions.create(['width', 'margin'], {
    easing: theme.transitions.easing.sharp,
    duration: theme.transitions.duration.leavingScreen,
  }),
  ...(open && {
    marginLeft: drawerWidth,
    width: `calc(100% - ${drawerWidth}px)`,
    transition: theme.transitions.create(['width', 'margin'], {
      easing: theme.transitions.easing.sharp,
      duration: theme.transitions.duration.enteringScreen,
    }),
  }),
}));

const Drawer = styled(MuiDrawer, { shouldForwardProp: (prop) => prop !== 'open' })(
  ({ theme, open }) => ({
    width: drawerWidth,
    flexShrink: 0,
    whiteSpace: 'nowrap',
    boxSizing: 'border-box',
    ...(open && {
      ...openedMixin(theme),
      '& .MuiDrawer-paper': openedMixin(theme),
    }),
    ...(!open && {
      ...closedMixin(theme),
      '& .MuiDrawer-paper': closedMixin(theme),
    }),
  }),
);

const MyMenu = styled(Menu)({
  padding: 0,
})

const MyMenuItem = styled(MenuItem)({
  paddingBottom: 0,
  paddingTop: 0,
  textAlign: 'center'
})


const Navbar = ({open, handleDrawerOpen, handleDrawerClose}) => {
  const navigate = useNavigate();
  const [anchorEl, setAnchorEl] = React.useState(null);
  const openDropdown = Boolean(anchorEl);
  const handleClick = (event) => {
    setAnchorEl(event.currentTarget);
  };
  const handleClose = async () => {
    const response = await SignOut();
    if(response != undefined && response.status === 200 && response.data === "you're logged out.") {
      navigate("/login");
    }
    console.log(response);
    setAnchorEl(null);
  };

  const handleRedirect = (index) => {
    console.log("ahh clicked", DrawerItemData[index]['page-url']);
    navigate(DrawerItemData[index]['page-url'])
  }
  const theme = useTheme();
  return (
    <>
      <AppBar position="fixed" open={open} style={{height: '40px', display: 'flex', justifyContent: 'center'}} >
        <Toolbar sx={{display:'flex', justifyContent: 'space-between'}}>
          <IconButton
            color="inherit"
            aria-label="open drawer"
            onClick={handleDrawerOpen}
            edge="start"
            sx={{
              marginRight: 5,
              ...(open && { display: 'none' }),
            }}
          >
            <MenuIcon />
          </IconButton>
          <Box flex={6} sx={{background: 'white', marginTop: 1, marginBottom: 1}} display='flex' borderRadius='5px'  >
          <Input placeholder="Search" sx={{ border: "none", boxShadow: 'none', paddingTop: 0, paddingBottom: 0}}>
                            </Input>
            <IconButton><GoSearch></GoSearch></IconButton>
          </Box>
          <Box display='flex' justifyContent='center' flex={2}>
            <IconButton color='inherit'>
                <Notifications />
            </IconButton>
            <IconButton onClick={handleClick} color='inherit'>
                <AccountCircle />
            </IconButton>
            <MyMenu
              id="basic-menu"
              anchorEl={anchorEl}
              open={openDropdown}
              onClose={handleClose}
              MenuListProps={{
                'aria-labelledby': 'basic-button',
              }}
            >
              <MyMenuItem onClick={handleClose} disableRipple={true} > Profile</MyMenuItem>
              <MyMenuItem onClick={handleClose}>My account</MyMenuItem>
              <MyMenuItem onClick={handleClose}>Logout</MyMenuItem>
            </MyMenu>
          </Box>
        </Toolbar>
      </AppBar>
      <Hidden >
        <Drawer variant="permanent" open={open}>
        <DrawerHeader>
        <Typography sx={{flexGrow:6, justifySelf: 'flex-start'}} variant="h6" noWrap component="div">
          Task Master
        </Typography>
          <IconButton onClick={handleDrawerClose}>
            {theme.direction === 'rtl' ? <ChevronRightIcon /> : <ChevronLeftIcon />}
          </IconButton>
        </DrawerHeader>
        <Divider />
        <List>
          {
              DrawerItemData.map((obj, index) => (
            <ListItem key={obj.title} disablePadding sx={{ display: 'block' }}>
              <ListItemButton
              onClick={() => handleRedirect(index)}
                sx={{
                  minHeight: 48,
                  justifyContent: open ? 'initial' : 'center',
                  px: 2.5,
                }}
              >
                <ListItemIcon
                  sx={{
                    minWidth: 0,
                    mr: open ? 3 : 'auto',
                    justifyContent: 'center',
                  }}
                >
                  {/* {index % 2 === 0 ? <InboxIcon /> : <MailIcon />} */}
                  {DrawerItemData[index].iconElement}
                </ListItemIcon>
                <ListItemText primary={obj.title} sx={{ opacity: open ? 1 : 0 }} />
                <ListItemText >0</ListItemText>
              </ListItemButton>
            </ListItem>
          ))}
        </List>
        <Divider />
        <List>
          {['All mail', 'Trash', 'Spam'].map((text, index) => (
            <ListItem key={text} disablePadding sx={{ display: 'block' }}>
              <ListItemButton
                sx={{
                  minHeight: 48,
                  justifyContent: open ? 'initial' : 'center',
                  px: 2.5,
                }}
              >
                <ListItemIcon
                  sx={{
                    minWidth: 0,
                    mr: open ? 3 : 'auto',
                    justifyContent: 'center',
                  }}
                >
                  {index % 2 === 0 ? <InboxIcon /> : <MailIcon />}
                </ListItemIcon>
                <ListItemText primary={text} sx={{ opacity: open ? 1 : 0 }} />
              </ListItemButton>
            </ListItem>
          ))}
        </List>
        </Drawer>
      </Hidden>
     </>
  );
};

export default Navbar;

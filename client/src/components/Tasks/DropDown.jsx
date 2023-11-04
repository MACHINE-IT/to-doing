// import * as React from 'react';
// import { Dropdown } from '@mui/base/Dropdown';
// import { Menu } from '@mui/base/Menu';
// import { MenuButton as BaseMenuButton } from '@mui/base/MenuButton';
// import { MenuItem as BaseMenuItem, menuItemClasses } from '@mui/base/MenuItem';
// import List from '@mui/material/List';
// import ListItem from '@mui/material/ListItem';
// import ListItemButton from '@mui/material/ListItemButton';
// import ListItemIcon from '@mui/material/ListItemIcon';
// import ListItemText from '@mui/material/ListItemText';
// import InboxIcon from '@mui/icons-material/Inbox';
// import Button from '@mui/material/Button';
// import Box from '@mui/material/Box';
// import Card from '@mui/material/Card';
// import FormGroup from '@mui/material/FormGroup';
// import FormControlLabel from '@mui/material/FormControlLabel';
// import Checkbox from '@mui/material/Checkbox';
// import {useState} from 'react';
// import TextField from '@mui/material/TextField';
// import Stack from '@mui/material/Stack';
// import Autocomplete from '@mui/material/Autocomplete';
//
// import { styled } from '@mui/system';
//
// export default function MenuSimple() {
//
//     const [isCardVisible, setIsCardVisible] = useState(false);
//
//
//     const createHandleMenuClick = (menuItem) => {
//         return () => {
//             console.log(`Clicked on ${menuItem}`);
//         };
//     };
//
//
//     const toggleVisibility = () => {
//         setIsCardVisible(!isCardVisible);
//     }
//
//
//     return (
//         <>
//             <Button variant="contained"  onClick={toggleVisibility} >Status</Button>
//             {
//                 isCardVisible &&
//                 <Card style={{position: "absolute", zIndex: 1}}  sx={{ width: 200 }} disablePadding  >
//                     <Stack spacing={2} sx={{ mt: 1 }} >
//                         <TextField
//                             label="Search status"
//                             InputProps={{
//                                 type: 'search',
//                             }}
//                         />
//                     </Stack>
//
//                     <List>
//                         <ListItem disablePadding>
//                             <ListItemButton disableRipple={true} disableTouchRipple={true}>
//                                 <ListItemIcon disableRipple={true} disableTouchRipple={true}>
//                                     <FormGroup disableRipple={true} disableTouchRipple={true}>
//                                         <FormControlLabel disableRipple={true} disableTouchRipple={true} control={<Checkbox disableRipple={true} disableTouchRipple={true}  />} label="In Progress" />
//                                     </FormGroup>
//                                 </ListItemIcon>
//                                 <ListItemText
//                                     // primary="Inbox"
//                                 />
//                             </ListItemButton>
//                         </ListItem>
//                     </List>
//                 </Card>
//             }
//
//         </>
//     );
// }
//
// const top100Films = [
//     { title: 'The Shawshank Redemption', year: 1994 },
//     { title: 'The Godfather', year: 1972 },
//     { title: 'The Godfather: Part II', year: 1974 },
//     { title: 'The Dark Knight', year: 2008 },
//     { title: '12 Angry Men', year: 1957 },
//     { title: "Schindler's List", year: 1993 },
//     { title: 'Pulp Fiction', year: 1994 },
//     {
//         title: 'The Lord of the Rings: The Return of the King',
//         year: 2003,
//     },
//     { title: 'The Good, the Bad and the Ugly', year: 1966 },
//     { title: 'Fight Club', year: 1999 },
//     {
//         title: 'The Lord of the Rings: The Fellowship of the Ring',
//         year: 2001,
//     },
//     {
//         title: 'Star Wars: Episode V - The Empire Strikes Back',
//         year: 1980,
//     },
//     { title: 'Forrest Gump', year: 1994 },
//     { title: 'Inception', year: 2010 },
//     {
//         title: 'The Lord of the Rings: The Two Towers',
//         year: 2002,
//     },
//     { title: "One Flew Over the Cuckoo's Nest", year: 1975 },
//     { title: 'Goodfellas', year: 1990 },
//     { title: 'The Matrix', year: 1999 },
//     { title: 'Seven Samurai', year: 1954 },
//     {
//         title: 'Star Wars: Episode IV - A New Hope',
//         year: 1977,
//     },
//     { title: 'City of God', year: 2002 },
//     { title: 'Se7en', year: 1995 },
//     { title: 'The Silence of the Lambs', year: 1991 },
//     { title: "It's a Wonderful Life", year: 1946 },
//     { title: 'Life Is Beautiful', year: 1997 },
//     { title: 'The Usual Suspects', year: 1995 },
//     { title: 'Léon: The Professional', year: 1994 },
//     { title: 'Spirited Away', year: 2001 },
//     { title: 'Saving Private Ryan', year: 1998 },
//     { title: 'Once Upon a Time in the West', year: 1968 },
//     { title: 'American History X', year: 1998 },
//     { title: 'Interstellar', year: 2014 },
//     { title: 'Casablanca', year: 1942 },
//     { title: 'City Lights', year: 1931 },
//     { title: 'Psycho', year: 1960 },
//     { title: 'The Green Mile', year: 1999 },
//     { title: 'The Intouchables', year: 2011 },
//     { title: 'Modern Times', year: 1936 },
//     { title: 'Raiders of the Lost Ark', year: 1981 },
//     { title: 'Rear Window', year: 1954 },
//     { title: 'The Pianist', year: 2002 },
//     { title: 'The Departed', year: 2006 },
//     { title: 'Terminator 2: Judgment Day', year: 1991 },
//     { title: 'Back to the Future', year: 1985 },
//     { title: 'Whiplash', year: 2014 },
//     { title: 'Gladiator', year: 2000 },
//     { title: 'Memento', year: 2000 },
//     { title: 'The Prestige', year: 2006 },
//     { title: 'The Lion King', year: 1994 },
//     { title: 'Apocalypse Now', year: 1979 },
//     { title: 'Alien', year: 1979 },
//     { title: 'Sunset Boulevard', year: 1950 },
//     {
//         title: 'Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb',
//         year: 1964,
//     },
//     { title: 'The Great Dictator', year: 1940 },
//     { title: 'Cinema Paradiso', year: 1988 },
//     { title: 'The Lives of Others', year: 2006 },
//     { title: 'Grave of the Fireflies', year: 1988 },
//     { title: 'Paths of Glory', year: 1957 },
//     { title: 'Django Unchained', year: 2012 },
//     { title: 'The Shining', year: 1980 },
//     { title: 'WALL·E', year: 2008 },
//     { title: 'American Beauty', year: 1999 },
//     { title: 'The Dark Knight Rises', year: 2012 },
//     { title: 'Princess Mononoke', year: 1997 },
//     { title: 'Aliens', year: 1986 },
//     { title: 'Oldboy', year: 2003 },
//     { title: 'Once Upon a Time in America', year: 1984 },
//     { title: 'Witness for the Prosecution', year: 1957 },
//     { title: 'Das Boot', year: 1981 },
//     { title: 'Citizen Kane', year: 1941 },
//     { title: 'North by Northwest', year: 1959 },
//     { title: 'Vertigo', year: 1958 },
//     {
//         title: 'Star Wars: Episode VI - Return of the Jedi',
//         year: 1983,
//     },
//     { title: 'Reservoir Dogs', year: 1992 },
//     { title: 'Braveheart', year: 1995 },
//     { title: 'M', year: 1931 },
//     { title: 'Requiem for a Dream', year: 2000 },
//     { title: 'Amélie', year: 2001 },
//     { title: 'A Clockwork Orange', year: 1971 },
//     { title: 'Like Stars on Earth', year: 2007 },
//     { title: 'Taxi Driver', year: 1976 },
//     { title: 'Lawrence of Arabia', year: 1962 },
//     { title: 'Double Indemnity', year: 1944 },
//     {
//         title: 'Eternal Sunshine of the Spotless Mind',
//         year: 2004,
//     },
//     { title: 'Amadeus', year: 1984 },
//     { title: 'To Kill a Mockingbird', year: 1962 },
//     { title: 'Toy Story 3', year: 2010 },
//     { title: 'Logan', year: 2017 },
//     { title: 'Full Metal Jacket', year: 1987 },
//     { title: 'Dangal', year: 2016 },
//     { title: 'The Sting', year: 1973 },
//     { title: '2001: A Space Odyssey', year: 1968 },
//     { title: "Singin' in the Rain", year: 1952 },
//     { title: 'Toy Story', year: 1995 },
//     { title: 'Bicycle Thieves', year: 1948 },
//     { title: 'The Kid', year: 1921 },
//     { title: 'Inglourious Basterds', year: 2009 },
//     { title: 'Snatch', year: 2000 },
//     { title: '3 Idiots', year: 2009 },
//     { title: 'Monty Python and the Holy Grail', year: 1975 },
// ];


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
import { useState } from 'react';
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import { styled } from '@mui/system';
import DeleteIcon from '@mui/icons-material/Delete';
import SendIcon from '@mui/icons-material/Send';
import Stack from '@mui/material/Stack';

export default function MenuSimple() {
    const [isCardVisible, setIsCardVisible] = useState(false);
    const [selected, setSelected] = useState([]);

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

    const [tasks, setTasks] = useState(tasksList);

    const createHandleMenuClick = (menuItem) => {
        return () => {
            console.log(`Clicked on ${menuItem}`);
        };
    };

    const toggleVisibility = () => {
        setIsCardVisible(!isCardVisible);
    };

    return (
        <>
            <Button  variant="contained" disableRipple onClick={toggleVisibility}>
                <div
                    style={{
                        display: 'flex',
                        justifyContent: 'spaced-between',
                        marginRight: '10px',
                    }}
                >
                    <svg
                        width="15"
                        height="15"
                        viewBox="0 0 15 15"
                        fill="none"
                        xmlns="http://www.w3.org/2000/svg"
                        class="mr-2 h-4 w-4"
                    >
                        <path
                            d="M7.49991 0.876892C3.84222 0.876892 0.877075 3.84204 0.877075 7.49972C0.877075 11.1574 3.84222 14.1226 7.49991 14.1226C11.1576 14.1226 14.1227 11.1574 14.1227 7.49972C14.1227 3.84204 11.1576 0.876892 7.49991 0.876892ZM1.82707 7.49972C1.82707 4.36671 4.36689 1.82689 7.49991 1.82689C10.6329 1.82689 13.1727 4.36671 13.1727 7.49972C13.1727 10.6327 10.6329 13.1726 7.49991 13.1726C4.36689 13.1726 1.82707 10.6327 1.82707 7.49972ZM7.50003 4C7.77617 4 8.00003 4.22386 8.00003 4.5V7H10.5C10.7762 7 11 7.22386 11 7.5C11 7.77614 10.7762 8 10.5 8H8.00003V10.5C8.00003 10.7761 7.77617 11 7.50003 11C7.22389 11 7.00003 10.7761 7.00003 10.5V8H4.50003C4.22389 8 4.00003 7.77614 4.00003 7.5C4.00003 7.22386 4.22389 7 4.50003 7H7.00003V4.5C7.00003 4.22386 7.22389 4 7.50003 4Z"
                            fill="currentColor"
                            fill-rule="evenodd"
                            clip-rule="evenodd"
                        ></path>
                    </svg>
                </div>
                Status <span> | </span>
            </Button>
            {isCardVisible && (
                <Card
                    style={{ position: 'absolute', zIndex: 1 }}
                    sx={{ width: 200 }}
                    disablePadding
                >
                    <Stack spacing={2} sx={{ mt: 1 }}>
                        <TextField
                            label="Search status"
                            InputProps={{
                                type: 'search',
                            }}
                        />
                    </Stack>

                    <List>
                        <ListItem disablePadding>
                            <ListItemButton disableRipple={true} disableTouchRipple={true}>
                                <ListItemIcon disableRipple={true} disableTouchRipple={true}>
                                    <FormGroup disableRipple={true} disableTouchRipple={true}>
                                        <FormControlLabel
                                            disableRipple={true}
                                            disableTouchRipple={true}
                                            control={
                                                <Checkbox
                                                    disableRipple={true}
                                                    disableTouchRipple={true}
                                                />
                                            }
                                            label="In Progress"
                                        />
                                    </FormGroup>
                                </ListItemIcon>
                                <ListItemText
                                    // primary="Inbox"
                                />
                            </ListItemButton>
                        </ListItem>
                        <ListItem disablePadding>
                            <ListItemButton disableRipple={true} disableTouchRipple={true}>
                                <ListItemIcon disableRipple={true} disableTouchRipple={true}>
                                    <FormGroup disableRipple={true} disableTouchRipple={true}>
                                        <FormControlLabel
                                            disableRipple={true}
                                            disableTouchRipple={true}
                                            control={
                                                <div>
                                                    <Checkbox
                                                        disableRipple={true}
                                                        disableTouchRipple={true}
                                                    />
                                                </div>
                                            }
                                            label={
                                                <div style={{width:'150px'}} >
                                                    <svg style={{marginRight:10}} width="15" height="15" viewBox="0 0 15 15" fill="none" xmlns="http://www.w3.org/2000/svg" class="mr-2 h-4 w-4 text-muted-foreground"><path d="M0.877075 7.49991C0.877075 3.84222 3.84222 0.877075 7.49991 0.877075C11.1576 0.877075 14.1227 3.84222 14.1227 7.49991C14.1227 11.1576 11.1576 14.1227 7.49991 14.1227C3.84222 14.1227 0.877075 11.1576 0.877075 7.49991ZM7.49991 1.82708C4.36689 1.82708 1.82708 4.36689 1.82708 7.49991C1.82708 10.6329 4.36689 13.1727 7.49991 13.1727C10.6329 13.1727 13.1727 10.6329 13.1727 7.49991C13.1727 4.36689 10.6329 1.82708 7.49991 1.82708Z" fill="currentColor" fill-rule="evenodd" clip-rule="evenodd"></path></svg>
                                                    <p style={{display:"inline-block"}}>Todo</p>
                                                </div>
                                            }
                                        >
                                        </FormControlLabel>
                                    </FormGroup>
                                </ListItemIcon>
                                <ListItemText
                                />
                            </ListItemButton>
                        </ListItem>
                        <ListItem disablePadding>
                            <ListItemButton disableRipple={true} disableTouchRipple={true}>
                                <ListItemIcon disableRipple={true} disableTouchRipple={true}>
                                    <FormGroup disableRipple={true} disableTouchRipple={true}>
                                        <FormControlLabel
                                            disableRipple={true}
                                            disableTouchRipple={true}
                                            control={
                                                <div>
                                                </div>
                                            }
                                            label={<p>clear filters</p>}
                                        >
                                        </FormControlLabel>
                                    </FormGroup>
                                </ListItemIcon>
                                <ListItemText
                                />
                            </ListItemButton>
                        </ListItem>
                    </List>

                </Card>
            )}
        </>
    );
}

const Status = {
    TODO: 'TODO',
    IN_PROGRESS: 'IN_PROGRESS',
    DONE: 'DONE',
    BACKLOG: 'BACKLOG',
    CANCELLED: 'CANCELLED',
};
const Priority = {
    HIGH: 'HIGH',
    MEDIUM: 'MEDIUM',
    LOW: 'LOW',
};
const taskSchema = {
    taskId: '',
    title: '',
    status: Status.TODO,
    priority: Priority.LOW,
};

function generateRandomTask() {
    const taskId = Math.random().toString(36).substring(7);
    const title = `Task ${taskId}`;
    const status =
        Object.values(Status)[
            Math.floor(Math.random() * Object.keys(Status).length)
            ];
    const priority =
        Object.values(Priority)[
            Math.floor(Math.random() * Object.keys(Priority).length)
            ];
    return { taskId, title, status, priority };
}

const tasks = [];
for (let i = 0; i < 20; i++) {
    tasks.push(generateRandomTask());
}

console.log(tasks);

const top100Films = [
    { title: 'The Shawshank Redemption', year: 1994 },
    { title: 'The Godfather', year: 1972 },
    { title: 'The Godfather: Part II', year: 1974 },
    { title: 'The Dark Knight', year: 2008 },
    { title: '12 Angry Men', year: 1957 },
    { title: "Schindler's List", year: 1993 },
    { title: 'Pulp Fiction', year: 1994 },
    {
        title: 'The Lord of the Rings: The Return of the King',
        year: 2003,
    },
    { title: 'The Good, the Bad and the Ugly', year: 1966 },
    { title: 'Fight Club', year: 1999 },
    {
        title: 'The Lord of the Rings: The Fellowship of the Ring',
        year: 2001,
    },
    {
        title: 'Star Wars: Episode V - The Empire Strikes Back',
        year: 1980,
    },
    { title: 'Forrest Gump', year: 1994 },
    { title: 'Inception', year: 2010 },
    {
        title: 'The Lord of the Rings: The Two Towers',
        year: 2002,
    },
    { title: "One Flew Over the Cuckoo's Nest", year: 1975 },
    { title: 'Goodfellas', year: 1990 },
    { title: 'The Matrix', year: 1999 },
    { title: 'Seven Samurai', year: 1954 },
    {
        title: 'Star Wars: Episode IV - A New Hope',
        year: 1977,
    },
    { title: 'City of God', year: 2002 },
    { title: 'Se7en', year: 1995 },
    { title: 'The Silence of the Lambs', year: 1991 },
    { title: "It's a Wonderful Life", year: 1946 },
    { title: 'Life Is Beautiful', year: 1997 },
    { title: 'The Usual Suspects', year: 1995 },
    { title: 'Léon: The Professional', year: 1994 },
    { title: 'Spirited Away', year: 2001 },
    { title: 'Saving Private Ryan', year: 1998 },
    { title: 'Once Upon a Time in the West', year: 1968 },
    { title: 'American History X', year: 1998 },
    { title: 'Interstellar', year: 2014 },
    { title: 'Casablanca', year: 1942 },
    { title: 'City Lights', year: 1931 },
    { title: 'Psycho', year: 1960 },
    { title: 'The Green Mile', year: 1999 },
    { title: 'The Intouchables', year: 2011 },
    { title: 'Modern Times', year: 1936 },
    { title: 'Raiders of the Lost Ark', year: 1981 },
    { title: 'Rear Window', year: 1954 },
    { title: 'The Pianist', year: 2002 },
    { title: 'The Departed', year: 2006 },
    { title: 'Terminator 2: Judgment Day', year: 1991 },
    { title: 'Back to the Future', year: 1985 },
    { title: 'Whiplash', year: 2014 },
    { title: 'Gladiator', year: 2000 },
    { title: 'Memento', year: 2000 },
    { title: 'The Prestige', year: 2006 },
    { title: 'The Lion King', year: 1994 },
    { title: 'Apocalypse Now', year: 1979 },
    { title: 'Alien', year: 1979 },
    { title: 'Sunset Boulevard', year: 1950 },
    {
        title:
            'Dr. Strangelove or: How I Learned to Stop Worrying and Love the Bomb',
        year: 1964,
    },
    { title: 'The Great Dictator', year: 1940 },
    { title: 'Cinema Paradiso', year: 1988 },
    { title: 'The Lives of Others', year: 2006 },
    { title: 'Grave of the Fireflies', year: 1988 },
    { title: 'Paths of Glory', year: 1957 },
    { title: 'Django Unchained', year: 2012 },
    { title: 'The Shining', year: 1980 },
    { title: 'WALL·E', year: 2008 },
    { title: 'American Beauty', year: 1999 },
    { title: 'The Dark Knight Rises', year: 2012 },
    { title: 'Princess Mononoke', year: 1997 },
    { title: 'Aliens', year: 1986 },
    { title: 'Oldboy', year: 2003 },
    { title: 'Once Upon a Time in America', year: 1984 },
    { title: 'Witness for the Prosecution', year: 1957 },
    { title: 'Das Boot', year: 1981 },
    { title: 'Citizen Kane', year: 1941 },
    { title: 'North by Northwest', year: 1959 },
    { title: 'Vertigo', year: 1958 },
    {
        title: 'Star Wars: Episode VI - Return of the Jedi',
        year: 1983,
    },
    { title: 'Reservoir Dogs', year: 1992 },
    { title: 'Braveheart', year: 1995 },
    { title: 'M', year: 1931 },
    { title: 'Requiem for a Dream', year: 2000 },
    { title: 'Amélie', year: 2001 },
    { title: 'A Clockwork Orange', year: 1971 },
    { title: 'Like Stars on Earth', year: 2007 },
    { title: 'Taxi Driver', year: 1976 },
    { title: 'Lawrence of Arabia', year: 1962 },
    { title: 'Double Indemnity', year: 1944 },
    {
        title: 'Eternal Sunshine of the Spotless Mind',
        year: 2004,
    },
    { title: 'Amadeus', year: 1984 },
    { title: 'To Kill a Mockingbird', year: 1962 },
    { title: 'Toy Story 3', year: 2010 },
    { title: 'Logan', year: 2017 },
    { title: 'Full Metal Jacket', year: 1987 },
    { title: 'Dangal', year: 2016 },
    { title: 'The Sting', year: 1973 },
    { title: '2001: A Space Odyssey', year: 1968 },
    { title: "Singin' in the Rain", year: 1952 },
    { title: 'Toy Story', year: 1995 },
    { title: 'Bicycle Thieves', year: 1948 },
    { title: 'The Kid', year: 1921 },
    { title: 'Inglourious Basterds', year: 2009 },
    { title: 'Snatch', year: 2000 },
    { title: '3 Idiots', year: 2009 },
    { title: 'Monty Python and the Holy Grail', year: 1975 },
];

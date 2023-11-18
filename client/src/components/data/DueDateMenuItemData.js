
import { Delete } from '@mui/icons-material';
import { BsSun } from 'react-icons/bs';
const days = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];

const today = new Date();
const tomorrow = new Date();
tomorrow.setDate(today.getDate() + 1);

const nextWeek = new Date();
nextWeek.setDate(today.getDate() + 7);

const DueDateMenuItemData = [
    {
        "title": "Today",
        "date": today,
        "iconElement": <BsSun></BsSun>,
        "day": days[today.getDay()]
    },
    {
        "title": "Tomorrow",
        "date": tomorrow,
        "iconElement": <BsSun></BsSun>,
        "day": days[tomorrow.getDay()]
    },
    {
        "title": "Next Week",
        "date": nextWeek,
        "iconElement": <BsSun></BsSun>,
        "day": days[nextWeek.getDay()]
    },
    {
        "title": "Pick a date",
        "date": null,
        "iconElement": <BsSun></BsSun>,
        "day": null
    },
    {
        "title": "Remove due date",
        "date": "null",
        "iconElement": <Delete></Delete>,
        "day": null
    }
]

export default DueDateMenuItemData;
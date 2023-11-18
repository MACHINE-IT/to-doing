
import { AiOutlineStar } from 'react-icons/ai';
import { CiUser } from 'react-icons/ci';
import { GrHomeRounded, GrPlan } from 'react-icons/gr'; //  BsPlusLg
// import {FcTodoList} from 'react-icons/fc'
import { BsSun } from 'react-icons/bs';

const DrawerItemData = [{
    "title": "My Day",
    "iconElement": <BsSun color='green'></BsSun>,
    "page-url": "/my-day"
},
{
    "title": "Important",
    "iconElement": <AiOutlineStar fontSize='16px'  color='brown'></AiOutlineStar>,
    "page-url": "/important"
},
{
    "title": "Planned",
    "iconElement": <GrPlan color='green'></GrPlan>,
    "page-url": "/planned"
},
{
    "title": "Assigned",
    "iconElement": <CiUser color='green'></CiUser>,
    "page-url": "/assigned-to"
},
{
    "title": "Tasks",
    "iconElement": <GrHomeRounded color='blue'></GrHomeRounded>,
    "page-url": "/tasks"
}]

export default DrawerItemData;
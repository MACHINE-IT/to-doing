import BaseURL, { axiosClient } from './commons/BaseURL';


const GET_ALL_TASKS_BY_USER_URL = `${BaseURL}/users/user/tasks`
const TestingUrl = `http://localhost:8081/test/hello-world/sdf`

let getAllTasks = async () => {
    let tasks ;
     try {
         tasks = await axiosClient.get(GET_ALL_TASKS_BY_USER_URL);
     }catch (error) {
        tasks = error;
     }
     return tasks;
}


let doSomeTesting = async () => {
    let tasks ;
    try {
        tasks = await axiosClient.get(TestingUrl);
        if(tasks.status === 200) {
            console.log("It works!");
            tasks = tasks.data;
        }
    }catch (e) {
        throw "Failed to fetch the tasks for this user " + e;
    }
    return tasks;
}
export { doSomeTesting, getAllTasks };


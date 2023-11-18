// TaskFactory.js
function createTask({ id, title, note, completed = false, due, isFavourite = false, steps }) {
    return {
      id,
      title,
      note,
      completed,
      due,
      isFavourite,
      steps
    };
  }
  
  export default createTask;
  
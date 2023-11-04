import React from 'react'
import { Route, useNavigate} from 'react-router-dom';

const ProtectedRoute = ({component: Component, isAuthenticated, ...rest}) => {

    const navigate = useNavigate();

    if(!isAuthenticated) {
        navigate("/login");
        return null;
    }

    return (
        <Route {...rest} element={<Component />} />
    )
}
export default ProtectedRoute

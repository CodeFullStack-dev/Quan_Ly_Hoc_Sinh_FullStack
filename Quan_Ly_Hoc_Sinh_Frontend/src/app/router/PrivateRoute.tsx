import { Navigate } from 'react-router-dom';
import { useAuthStore } from '@/app/store/authStore';

const PrivateRoute = ({ children }: { children: JSX.Element }) => {
    const token = useAuthStore((state) => state.token);

    if (!token) {
        return <Navigate to="/login" replace />;
    }

    return children;
}

export default PrivateRoute;

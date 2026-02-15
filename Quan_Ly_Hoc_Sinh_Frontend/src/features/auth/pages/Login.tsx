import { useState } from "react";
import { loginApi } from "../api/authApi";
import { useAuthStore } from "@/app/store/authStore";
import { useNavigate } from "react-router-dom";

const Login = () => {
    const navigate = useNavigate();
    const setAuth = useAuthStore((state) => state.setAuth);

    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    const handleLogin = async () => {
        try {
            const data = await loginApi({ username, password });
            setAuth(data);
            navigate("/dashboard");
        } catch (error) {
            alert("Lỗi đăng nhập: ");
            
        }
    };

     return (
    <div className="flex flex-col gap-3 p-10">
      <input
        className="border p-2"
        placeholder="Username"
        onChange={(e) => setUsername(e.target.value)}
      />
      <input
        className="border p-2"
        type="password"
        placeholder="Password"
        onChange={(e) => setPassword(e.target.value)}
      />
      <button
        className="bg-blue-500 text-white p-2"
        onClick={handleLogin}
      >
        Login
      </button>
    </div>
  );
};

export default Login;



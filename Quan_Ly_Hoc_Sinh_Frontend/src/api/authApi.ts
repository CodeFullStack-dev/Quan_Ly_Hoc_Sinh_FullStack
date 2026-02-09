import axiosClient from "./axiosClient";
import { LoginRequest, JwtResponse, EmployeeRequest, EmployeeResponse, ProfileResponse } from "../types/auth";

export const authApi = {
    login: (data: LoginRequest) => axiosClient.post<JwtResponse>('/auth/login', data),
    register: (data: EmployeeRequest) => axiosClient.post<EmployeeResponse>('/auth/register', data),
    getProfile: () => axiosClient.get<ProfileResponse>('/auth/profile'),
}
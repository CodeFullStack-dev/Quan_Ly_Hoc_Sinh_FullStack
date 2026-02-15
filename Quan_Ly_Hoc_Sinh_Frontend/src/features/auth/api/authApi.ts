import axiosClient from "@/lib/axiosClient";

export interface LoginRequest {
    username: string;
    password: string;
}

export interface LoginResponse {
    jwtToken: string;
    username: string;
    role: string;
}

export const loginApi = async (
  data: LoginRequest
): Promise<LoginResponse> => {
  const response = await axiosClient.post("/auth/login", data);
  return response.data;
};

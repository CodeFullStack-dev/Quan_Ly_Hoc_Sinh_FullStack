import { create } from "zustand";
import { persist } from "zustand/middleware";

interface AuthState {
  token: string | null;
  username: string | null;
  role: string | null;

  isAuthenticated: boolean;

  setAuth: (data: { jwtToken: string; username: string; role: string }) => void;
  logout: () => void;
}

export const useAuthStore = create<AuthState>()(
  persist(
    (set) => ({
      token: null,
      username: null,
      role: null,
      isAuthenticated: false,

      setAuth: ({ jwtToken, username, role }) =>
        set({
          token: jwtToken,
          username,
          role,
          isAuthenticated: true,
        }),

      logout: () =>
        set({
          token: null,
          username: null,
          role: null,
          isAuthenticated: false,
        }),
    }),
    {
      name: "auth-storage",
    },
  ),
);

import { Role } from "./role";

export interface User{
    id?: number;
    username: string;
    email?: string;
    password: string;
    confirmPassword: string;
    roles?: Role[];
}
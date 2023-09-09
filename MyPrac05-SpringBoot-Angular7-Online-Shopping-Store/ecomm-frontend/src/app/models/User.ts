import {Role} from "../enum/Role";

export class User {

    email: string | undefined;

    password: string | undefined;

    name: string | undefined;

    phone: string | undefined;

    address: string | undefined;

    active: boolean | undefined;

    role: string | undefined;

    constructor(){
        this.active = true;
        this.role = Role.Customer;
    }
}

import { Injectable } from 'angular2/core';

export interface Customer {
    id: number;
    name: string;
}

@Injectable()
export class CustomerService {
}

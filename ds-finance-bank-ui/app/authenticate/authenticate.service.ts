import { Injectable } from 'angular2/core';

export interface Authenticate {
  email: string;
  password: string;
}

@Injectable()
export class AuthenticateService {
}

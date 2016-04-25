import { Injectable } from 'angular2/core';
import { Company } from '../companies/company.service';

export interface Transaction {
  id: number;
  numberOfShares: number,
  state: string,
  price: number,
  message: string,
  action: string,
  company: Company
}

@Injectable()
export class TransactionService {
}

import { Injectable } from 'angular2/core';
import { Company } from '../companies/company.service';

export interface Share {
  numberOfShares: number;
  currentValue: string;
  company: Company
}

@Injectable()
export class ShareService {
}

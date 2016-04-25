import { Injectable } from 'angular2/core';

export interface Company {
  id: number;
  symbol: string,
  name: string,
  lastTradingPrice: number,
  floatShares: number,
  stockExchange: string
}

@Injectable()
export class CompanyService {
}

import { User } from './user';

export class Ticket {

    id: number;
    requestType: string;
    priority: string;
    travelCity: string;
    fromCity: string;
    startDate: Date;
    endDate: Date;
    passportNumber: string;
    projectName: string;
    expenseBourneBy: string;
    travelApprover: string;
    expectedDuration: string;
    upperBound: string;
    additionalDetails: string;
    status: string;
    updatedAt: string;
    commentByAdmin: string;
    userId: number;
    user: User;
    constructor(){}
}

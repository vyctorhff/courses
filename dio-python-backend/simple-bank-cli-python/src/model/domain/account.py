from src.model.exceptions.exceptions import AccountInvalidError

class Account:

    def __init__(self, number, agency='0001'):
        self.number = number
        self.agency = agency

    def validate(self) -> None:
        if self.number == None or type(self.number) != int:
            raise AccountInvalidError('Invalid account number')
        
        if self.agency == None or type(self.agency) != str:
            raise AccountInvalidError('Invalid agency number')

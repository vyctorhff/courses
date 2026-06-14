from src.model.domain.wallet import Wallet

class User:
    
    def __init__(self, name, email, current_value, account=None):
        self.name = name
        self.email = email
        self.wallet = Wallet(current_value)
        self.accounts = []

        if account != None:
            self.add_account(account)
    
    def add_account(self, account):
        account.validate()
        self.accounts.append(account)

    def validate(self) -> bool:

        if self.name.strip() == '':
            return False

        if self.email.strip() == '':
            return False
        
        if self.wallet.get_value() == None:
            return False;

        return True

    def is_equal(self, user):
        return self.email == user.email

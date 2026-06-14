class TransactionInMemoryRepository:

    def __init__(self):
        self.list = list()
    
    def add(self, transaction) -> None:
        self.list.append(transaction)
    
    def find_by_user_sorce_email(self, email) -> list:
        func_filter = lambda transac: transac.check_user_source_email(email)
        return list(filter(func_filter, self.list))
    
    def find_by_user_email(self, email) -> list:
        func_filter = lambda transac: transac.check_both_users_by_email(email)
        return list(filter(func_filter, self.list))

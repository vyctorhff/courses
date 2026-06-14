class TransactionService:

    def __init__(self, transaction_repository):
        self.transactions = list()
        self.transaction_repository = transaction_repository

    def add(self, transaction):
        self.transaction_repository.add(transaction)

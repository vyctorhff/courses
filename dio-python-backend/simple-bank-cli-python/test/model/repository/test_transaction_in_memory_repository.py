import unittest

from src.model.domain.transaction import Transaction
from src.model.domain.user import User

from src.model.repository.transaction_in_memory_repository import TransactionInMemoryRepository

class TestTransactionInMemoryRepository(unittest.TestCase):

    def setUp(self):
        self.repository = TransactionInMemoryRepository()

    def create_transacation(self) -> Transaction:
        user_source = User('name1', 'email1', 100.0)
        user_target = User('name2', 'email2', 200.0)
        return Transaction(100.0, user_source, user_target)

    def test_add_transaction(self):
        self.repository.add(self.create_transacation())

    def test_find_by_user_sorce_email(self):
        self.repository.add(self.create_transacation())
        self.repository.add(self.create_transacation())

        list_transaction = self.repository.find_by_user_sorce_email('email1')

        self.assertIsNotNone(list_transaction)
        self.assertEqual(len(list_transaction), 2)

        transaction = list_transaction.pop()
        self.assertEqual(100.0, transaction.value)

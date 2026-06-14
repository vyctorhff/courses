import unittest

from src.model.domain.wallet import Wallet

class WalletTest(unittest.TestCase):

    def setUp(self):
        pass

    def test_successfully(self):
        wallet = Wallet(100.0)
        self.assertEqual(100.0, wallet.get_value())
    
    def test_negative_current_value(self):
        with self.assertRaises(ValueError):
            Wallet(-1.0)

    def test_debit(self):
        wallet = Wallet(100.0)

        before = wallet.get_value()
        wallet.debit(10.0)
        after = wallet.get_value()

        self.assertNotEqual(before, after)
        self.assertEqual(90.0, wallet.get_value())
    
    def test_credit(self):
        wallet = Wallet(100.0)

        before = wallet.get_value()
        wallet.credit(10.0)
        after = wallet.get_value()

        self.assertNotEqual(before, after)
        self.assertEqual(110.0, wallet.get_value())
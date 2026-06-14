from src.view.messages import STAMENT_MAIN, STAMENT_TRANSACTION_DETAIL, STAMENT_NO_TRANSACTIONS

class StatmentView:

    def __init__(self):
        pass

    def print_main(self):
        print(STAMENT_MAIN)

    def print_transaction(self, user, transactions):
        if len(transactions) == 0:
            print(STAMENT_NO_TRANSACTIONS)
            return

        for transaction in transactions:
            opt = transaction.get_operation_by_user(user)
            date = transaction.dt
            value = transaction.value
            user = transaction.user_target.name
            print(STAMENT_TRANSACTION_DETAIL.format(opt, user, value, date))

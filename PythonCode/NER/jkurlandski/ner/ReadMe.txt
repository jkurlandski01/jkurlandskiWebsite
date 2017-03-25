self.assertRaises(SomeCoolException, mymod.myfunc)

with self.assertRaises(Exception) as context:
            broken_function()
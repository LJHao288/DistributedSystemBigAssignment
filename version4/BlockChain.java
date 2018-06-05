import java.util.Arrays;
import java.util.ArrayList;
import java.lang.reflect.Array;
public class BlockChain{
	private ArrayList<Block> blockChain ;
    
	public BlockChain(){
		this.blockChain = new ArrayList<Block>();
		String[] genesisTransactions = {"BlockChainBegin"};
		Block genesisBlock = new Block(0,genesisTransactions);
		this.blockChain.add(genesisBlock);
	}
	
	public void addBlock(String[] transactions){
		Block lastBlock = this.blockChain.get(blockChain.size()-1);
		Block blockToAdd = new Block(lastBlock.getBlockHash(),transactions);
		this.blockChain.add(blockToAdd);
	}
	
	@Override
	public String toString() {
		String result = "";
		for (Block var : blockChain){
			String[] ts = var.getTransaction();
			result += ts[0];
			result += "\n";
		}
		return result;
	}

}
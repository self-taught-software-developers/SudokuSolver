package com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.framework.manager

import androidx.camera.core.ExperimentalGetImage
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.latin.TextRecognizerOptions
import com.stsd.selftaughtsoftwaredevelopers.androidsudokusolver.ui.model.TileState

class SudokuBoardAnalyzer(
    private val tileList: List<TileState>,
    val onProcessed: (TileState) -> Unit
) : ImageAnalysis.Analyzer {

    private val recognizer = TextRecognition.getClient(TextRecognizerOptions.Builder().build())
    private val processedElements = hashMapOf<Pair<Int,Int>,TileState>()

    @ExperimentalGetImage
    override fun analyze(imageProxy: ImageProxy) {

        imageProxy.image?.let { mediaImage ->
            val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

            recognizer.process(image)
                .addOnCompleteListener { result ->

//                    if (result.isSuccessful) {
//
//                        //TODO Based on the image's bounding box place a text on the screen.
//                        //TODO convert a boundingBox to an xy tile placement.
//                        result.result
//                            .textBlocks.flatMap { block ->
//                                block.lines.flatMap { line ->
//                                    line.elements
//                                }
//                            }.forEach { element ->
//
//                                element.text.take(1).toIntOrNull()?.let { value ->
//                                    element.boundingBox?.toComposeRect()?.let { rect ->
//                                        tileList
//                                            .firstOrNull { tile ->
//                                                tile.rect?.contains(rect.topLeft) == true
//                                            }?.let { tile ->
//                                                //TODO hash of items already added.
//                                                //TODO add it
////                                                println("$value, ${tile.position}, ${tile.rect}, $rect")
//                                                if (processedElements.contains(tile.position)) {
//                                                    //todo change the value if they are different.
//                                                } else {
//
////                                                    processedElements[tile.position] = TileState(
////                                                        text = value.toString(),
////                                                        position = tile.position
////                                                    ).apply(onProcessed)
//
//                                                }
//
//                                            }
//
//                                    }
//
//                                }
//
//                            }
//
//                    }

                    imageProxy.close()
                }

        }
    }

//    val barcodeInCenter = task.result.firstOrNull { barcode ->
//
//        val barcodeBoundingBox = barcode?.boundingBox?.toRectF()
//            ?: return@firstOrNull false
//
//        "${overlayBoundingBox}, $barcodeBoundingBox".coreDataLogIt("BarcodeAnalyzer")
//
//        overlayBoundingBox.intersect(barcodeBoundingBox)
//
//    }

}